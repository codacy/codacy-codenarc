# codacy-codenarc

Docker engine to allow Codacy to have [CodeNarc](https://github.com/CodeNarc/CodeNarc) support.

## Usage

You can create the docker by doing:

    sbt docker:publishLocal

The docker is ran with the following command:

    docker run -it -v $srcDir:/src <DOCKER_NAME>:<DOCKER_VERSION>

To run the tool using a custom configuration file, run docker with the following command:

    docker run -it -v $srcDir:/src -v $codacyrcConfig:/.codacyrc <DOCKER_NAME>:<DOCKER_VERSION>

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
