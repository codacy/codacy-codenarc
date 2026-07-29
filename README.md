# codacy-codenarc

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2ac2eab19b9742d09074afb40bc932ff)](https://app.codacy.com/gh/codacy/codacy-codenarc?utm_source=github.com&utm_medium=referral&utm_content=codacy/codacy-codenarc&utm_campaign=Badge_Grade_Settings)

Docker engine to allow Codacy to have [CodeNarc](https://github.com/CodeNarc/CodeNarc) support.

## Usage

You can create the docker by doing:

    sbt docker:publishLocal

The docker is ran with the following command:

    docker run -it -v $srcDir:/src <DOCKER_NAME>:<DOCKER_VERSION>

To run the tool using a custom configuration file, run docker with the following command:

    docker run -it -v $srcDir:/src -v $codacyrcConfig:/.codacyrc <DOCKER_NAME>:<DOCKER_VERSION>

## Agent Playbook: Updating This Repository End-to-End

This section is written for an AI coding agent (or a human) tasked with updating this repo — most commonly bumping the wrapped CodeNarc version, but also base image / orb bumps. Follow it top to bottom; it tells you what to change, how to regenerate derived files, how to test locally, and how to interpret CI so you can iterate on failures without guessing.

### 1. What this repository is

This is a **Codacy engine**: a thin Scala wrapper (`src/main/scala/codacy/Engine.scala`, `CodeNarc.scala`, `CodeNarcOutput.scala`, built on the `codacy-engine-scala-seed` library) that packages [CodeNarc](https://github.com/CodeNarc/CodeNarc) — a static analysis tool for Groovy code — as a Docker image Codacy's platform can run against a customer's source code. The `docs/` directory (packaged from `src/main/resources/docs`) is not just documentation — it is **machine-consumed configuration**:

- `docs/patterns.json` — the full list of CodeNarc rules ("patterns") Codacy knows about, their parameters/defaults, and which are enabled out of the box (enabled = present in `DefaultRules.list`). Generated file, do not hand-edit.
- `docs/description/description.json` + `docs/description/<PatternId>.md` — human-readable titles/descriptions per pattern, used in the Codacy UI. Generated files, do not hand-edit.
- `docs/tests/*.groovy` and `docs/multiple-tests/{with-config-file,without-config-file}/*` — fixtures used by `codacy-plugins-test` to validate the engine actually produces the results it claims to for real Groovy code samples.
- `docs/tool-description.md` — short blurb about the tool, hand-maintained.

All the generated artifacts above come from **`DocGenerator`** (`docgenerator/src/main/scala/codacy/codenarc/docs/DocGenerator.scala`, a separate sbt subproject named `docGenerator`). It uses `GitHelper.withRepository` (`docgenerator/src/main/scala/codacy/codenarc/docs/GitHelper.scala`) to shallow-clone the real `github.com/CodeNarc/CodeNarc.git` repo at tag `v<toolVersion>` into a temp directory, then scrapes its `docs/codenarc-rule-index.md` and per-rule markdown files (via `MarkdownHelper`) plus reflects over the `org.codenarc.rule.AbstractAstVisitorRule` subclasses on the classpath (via `org.reflections.Reflections`) to build the pattern list, parameter defaults, and priorities. This means the generator needs **network access** and **git** installed locally, and it reads the CodeNarc version to check out from `Versions.codenarcVersion` (an auto-generated `Compile / sourceGenerators` object, itself derived from `toolVersion` in `build.sbt`) unless overridden by a CLI arg.

### 2. Files that encode versions — check all of these on every update

| File | What it controls | What to check |
|---|---|---|
| `build.sbt` → `ThisBuild / toolVersion` | Which CodeNarc release is bundled (also drives `libraryDependencies` for both the `root` and `docGenerator` subprojects, and the generated `Versions.codenarcVersion`) | Bump to the target version, confirm a matching upstream tag (`v<version>`) exists in `CodeNarc/CodeNarc` on GitHub, and that the version is published to Maven as `org.codenarc % CodeNarc`. |
| `build.sbt` → `dockerBaseImage` | Runtime the packaged app runs on (currently `amazoncorretto:8-alpine3.21-jre`) | Only bump if the new CodeNarc version raises its minimum JRE requirement, or as a routine base-image refresh (see commit `646f948`/`b1e253c` history for precedent). |
| `.circleci/config.yml` → `codacy/base` orb | Shared CircleCI steps (checkout/version, sbt build, docker publish/tag) | Check the latest published version of the `codacy/base` orb. |
| `.circleci/config.yml` → `codacy/plugins-test` orb | Runs `codacy-plugins-test` in CI | Check the latest published version of the `codacy/plugins-test` orb. |
| `project/plugins.sbt` → `codacy-sbt-plugin` | sbt plugin providing shared Codacy sbt settings/Docker packaging | Bump only if instructed or if it is blocking a needed sbt feature. |

There is no separate lockfile pinning the CodeNarc version anywhere else — `build.sbt`'s `toolVersion` setting is the single source of truth that propagates everywhere else (Docker image contents, generated `Versions.scala`, and `docs/patterns.json`'s `"version"` field via regeneration).

### 3. Step-by-step update procedure

1. **Bump `ThisBuild / toolVersion` in `build.sbt`** to the target CodeNarc version (and `dockerBaseImage` if scoped by the task).
2. **Regenerate the docs** by running the doc generator:
   ```
   sbt "docGenerator / runMain codacy.codenarc.docs.DocGenerator"
   ```
   This clones the upstream CodeNarc repo at the new tag and rewrites `src/main/resources/docs/patterns.json`, `src/main/resources/docs/description/description.json`, and `src/main/resources/docs/description/<PatternId>.md` for every rule. Review the diff carefully for new/removed/renamed patterns (CodeNarc sometimes renames or drops rules between versions) and check whether any newly-added patterns should be listed in `docgenerator/src/main/scala/codacy/codenarc/docs/DefaultRules.scala` (this list controls which patterns are `enabled` by default; cross-reference against CodeNarc's `StarterRuleSet-AllRulesByCategory.groovy.txt`).
3. **Compile, format, and test** using sbt (the same commands CI runs):
   ```
   sbt "set scalafmtUseIvy in ThisBuild := false; scalafmt::test; test:scalafmt::test; sbt:scalafmt::test; test"
   ```
   Fix any formatting violations with `sbt scalafmt` / `sbt test:scalafmt` / `sbt sbt:scalafmt` before re-running the check.
4. **Build the Docker image** locally:
   ```
   sbt docker:publishLocal
   ```
5. **Run `codacy-plugins-test` locally** before pushing — clone https://github.com/codacy/codacy-plugins-test and run its Docker-based tests (`run_multiple_tests` mode, matching what CI's `codacy_plugins_test/run` step does) against your freshly built local image tag.
6. **Iterate on failures**, re-running only the relevant test command after each fix.
7. **Commit** the version bump(s) in `build.sbt` together with the regenerated `docs/patterns.json` and `docs/description/*` files in one change (this mirrors the repo's own history, e.g. commit `f16d26c` "bump codenarc 3.6.0").
8. **Push and open a PR.**
9. **Poll the PR's real CI checks until they all pass — local validation is NOT the finish line.** After every push, run `gh pr checks <pr-url>` and keep re-polling (short sleep while any check is `pending`) until all checks finish. If a check fails, fetch its actual log (don't guess), find the true root cause, fix it, push again (never `--no-verify`, never force-push), and re-poll. Repeat until every check is green. **The CI environment's toolchain can differ from your local one**, so a clean local run does not guarantee CI passes. Only stop iterating when every check passes, or you hit a genuine product/infra decision that needs a human.

### 4. Common failure modes and fixes

| Symptom | Likely cause | Fix |
|---|---|---|
| `DocGenerator` fails to clone / hangs | No network access in the sandbox, or the tag `v<toolVersion>` doesn't exist upstream | Verify the exact tag name on `github.com/CodeNarc/CodeNarc` (CodeNarc tags releases as `vX.Y.Z`) before bumping `toolVersion`. |
| Reflection-based parameter extraction throws or silently drops a rule's parameters | A CodeNarc rule class changed its field types/names between versions (`getParametersFromType` only accepts `Int`/`String`/`Boolean` fields with lowercase names) | Compare the rule's source in the cloned CodeNarc checkout; parameters that don't fit this shape are simply omitted from `patterns.json`, which is expected upstream behavior, not a bug to "fix". |
| `docs/patterns.json` diff shows a pattern flipping from `enabled: true` to `false` (or vice versa) unexpectedly | `DefaultRules.list` wasn't updated for a renamed rule | Check `DefaultRules.list` in `docgenerator/.../DefaultRules.scala` against the new rule name. |

### 5. Definition of done

- `ThisBuild / toolVersion` (and `dockerBaseImage`, if applicable) bumped in `build.sbt`.
- `docs/patterns.json` and `docs/description/*` regenerated via the `DocGenerator` sbt task and committed, with any `DefaultRules` inconsistencies resolved.
- `sbt scalafmt::test` / `test:scalafmt::test` / `sbt:scalafmt::test` and `sbt test` pass.
- `sbt docker:publishLocal` builds the Docker image successfully.
- `codacy-plugins-test` commands all pass locally against the freshly built image.
- **After pushing and opening/updating the PR, every CI check on it is green.** Poll `gh pr checks <pr-url>` and iterate on any failure until all pass.

## Tool Developer Documentation

[Tool Developer Guide](https://support.codacy.com/hc/en-us/articles/207994725-Tool-Developer-Guide)


[Tool Developer Guide - Using Scala](https://support.codacy.com/hc/en-us/articles/207280379-Tool-Developer-Guide-Using-Scala)


## Structure

- To run the tool we provide the configuration file, ```/.codacyrc```, with the language to run and optional parameters your tool might need.
- The source code to be analysed will be located in ```/src```, meaning that when provided in the configuration, the file paths are relative to ```/src```.

#### Configuration file (.codacyrc)

This file has:

 - files: Files to be analysed (their path is relative to ```/src```)
 - tools: Array of tools
 - name: Unique identifier of the tool. This will be provided by the tool in patterns.json file.
 - patterns: Array of patterns that must be checked
     - patternId: Unique identifier of the pattern
     - parameters: Parameters of the pattern
     - name: Unique identifier of the parameter
     - value: Value to be used as parameter value

```
{
  "files" : ["foo/bar/baz.groovy", "foo2/bar/baz.groovy"],
  "tools":[
    {
      "name":"codenarc",
      "patterns":[
        {
          "patternId":"AssignmentInConditional"
        }
      ]
    }
  ]
}
```

#### Tool documentation

At Codacy we strive to provide the best value to our users and, to accomplish that, we document our patterns so that the user can better understand the problem and fix it.

The documentation for the tool must always be updated before submitting the docker.

Your files for this section should be placed in ```/docs/description/```.

In order to provide more details you can create:

- A single ```/docs/description/description.json```
- A ```/docs/description/<PATTERN-ID>.md``` for each pattern


In the description.json you define the title for the pattern, brief description, time to fix (in minutes), and also a description of the parameters in the following format:

```
[
  {
    "patternId" : "EmptyCatchBlock",
    "title" : "EmptyCatchBlock",
    "description" : "EmptyCatchBlock.description=In most cases, exceptions should not be caught and ignored (swallowed).",
    "parameters" : [ {
      "name" : "ignoreRegex",
      "description" : ""
    } ]
  }
]
```

To give a more detailed explanation about the issue, you should define the ```<PATTERN-ID>.md```. Example:

```
Checks for empty *catch* blocks. In most cases, exceptions should not be caught and ignored (swallowed).

The rule has a property named `ignoreRegex` that defaults to the value 'ignore|ignored'. If the name of the exception
matches this regex then no violations are produced.

| Property                    | Description            | Default Value    |
|-----------------------------|------------------------|------------------|
| ignoreRegex                 | Regular expression - exception parameter names matching this regular expression are ignored and no violations are produced. | 'ignore\|ignored' |

Here is an example of code that produces a violation:

        def myMethod() {
        try {
            doSomething
        } catch(MyException e) {                //violation
            // should do something here
        }
    }

    def myMethod() {
        try {
            doSomething
        } catch(MyException ignored) {
            //no violations because the parameter name is ignored
        }
    }
```

This documentation should be generated automatically by using the Documentation Generator tool:

```
sbt "docGenerator / runMain codacy.codenarc.docs.DocGenerator"
```


#### Test

Follow the instructions at [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test).


## What is Codacy

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors
your technical debt, helps you improve your code quality, teaches best practices
to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features

* Identify new Static Analysis issues
* Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and
  also direct git repositories)
* Auto-comments on Commits and Pull Requests
* Integrations with Slack, HipChat, Jira, YouTrack
* Track issues in Code Style, Security, Error Proneness, Performance, Unused
  Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code
Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
