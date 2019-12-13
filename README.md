# codacy-codenarc

Docker engine to allow Codacy to have [CodeNarc](https://github.com/CodeNarc/CodeNarc) support.

## Usage

You can create the docker by doing:

    sbt docker:publishLocal

The docker is ran with the following command:

    docker run -it -v $srcDir:/src <DOCKER_NAME>:<DOCKER_VERSION>

To run the tool using a custom configuration file, run docker with the following command:

    docker run -it -v $srcDir:/src -v $codacyrcConfig:/.codacyrc <DOCKER_NAME>:<DOCKER_VERSION>


## Docs

[Tool Developer Guide](https://support.codacy.com/hc/en-us/articles/207994725-Tool-Developer-Guide)


[Tool Developer Guide - Using Scala](https://support.codacy.com/hc/en-us/articles/207280379-Tool-Developer-Guide-Using-Scala)


## Configuration file (.codacyrc)

To run the tool we provide the configuration file, /.codacyrc, with the language to run and optional parameters your tool might need.

#### Structure
This file has:

 - files: Files to be analysed (their path is relative to /src)
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
