*Since CodeNarc 3.5.0*

CPS transformed methods may not be called from non CPS transformed
methods in Jenkins. Every analyzed Method is assumed to be CPS
transformed, except it is annotated with
`com.cloudbees.groovy.cps.NonCPS`, is a constructor or a library method
that is not configured in `cpsPackages`. Pipeline steps are assumed to
be CPS transformed except for ‘echo’, ‘properties’ and ‘getContext’. See
also the Jenkins docs on [Pipeline CPS Method
Mismatches](https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches/).

<table>
<colgroup>
<col style="width: 25%" />
<col style="width: 57%" />
<col style="width: 16%" />
</colgroup>
<thead>
<tr>
<th>Property</th>
<th>Description</th>
<th>Default Value</th>
</tr>
</thead>
<tbody>
<tr>
<td>cpsScriptVariableName</td>
<td>Jenkins pipeline script variable name</td>
<td>‘script’</td>
</tr>
<tr>
<td>cpsPackages</td>
<td>Package names of used Jenkins pipeline libraries</td>
<td>[]</td>
</tr>
</tbody>
</table>

Known limitations:

- Methods are only checked for their name, parameters are ignored.
  Therefore false positives are possible if two overloaded methods are
  both CPS and non CPS transformed respectively.
- Because of their dynamic nature, pipeline steps can only be recognized
  if they are called on a predefined script variable (default is
  `script`).
- Method calls on dynamic types (Object) can’t be resolved.

Examples:

    class SomeClass() {
        public int value = cpsMethod() // Violation
        SomeClass() {}
    }

    @NonCPS
    void nonCpsMethod() {}

    void cpsMethod() {}

    @NonCPS
    void someMethod() {
        nonCpsMethod() // OK
        new SomeClass() // OK
        cpsMethod() // Violation
        script.sh('echo hello') // Violation
    }
