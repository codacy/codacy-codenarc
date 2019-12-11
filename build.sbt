import sjsonnew._
import sjsonnew.BasicJsonProtocol._
import sjsonnew.support.scalajson.unsafe._
<<<<<<< HEAD

=======
>>>>>>> Call CodeNarc tool with passed configuration
import com.typesafe.sbt.packager.Keys.{
  daemonUser,
  defaultLinuxInstallLocation,
  dockerBaseImage,
  dockerCmd,
  dockerEntrypoint,
  dockerUpdateLatest,
  maintainer,
  packageName
}
import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}


organization := "com.codacy"

name := "codacy-codenarc"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.10"

// Tool version
lazy val toolVersion = settingKey[String](
  "The version of the underlying tool retrieved from patterns.json")
toolVersion := {
  case class Patterns(name: String, version: String)
  implicit val patternsIso: IsoLList[Patterns] =
    LList.isoCurried((p: Patterns) =>
      ("name", p.name) :*: ("version", p.version) :*: LNil) {
      case (_, n) :*: (_, v) :*: LNil => Patterns(n, v)
    }

  val jsonFile = (resourceDirectory in Compile).value / "docs" / "patterns.json"
  val json = Parser.parseFromFile(jsonFile)
  val patterns = json.flatMap(Converter.fromJson[Patterns])
  patterns
    .map(_.version)
    .getOrElse(
      throw new Exception("Failed to retrieve version from docs/patterns.json"))
}

libraryDependencies ++= Seq(
  "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0",
  "org.codenarc" % "CodeNarc" % toolVersion.value,
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "org.slf4j" % "slf4j-nop" % "1.7.12",
  "org.scalatest" % "scalatest_2.12" % "3.1.0" % Test
)

scalacOptions.in(Compile, console) --= Seq("-Ywarn-unused",
  "-Ywarn-unused:imports",
  "-Xfatal-warnings")
scalacOptions in Test ++= Seq("-Yrangepos")

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin)

// write codenarc version into .codenarc-version
lazy val codeNarcVersionFile = Def.setting {
  val f = file(".codenarc-version")
  IO.write(f, toolVersion.value)
  f
}

resourceDirectory in IntegrationTest := (baseDirectory apply {baseDir: File => baseDir / "test/resources"}).value

mappings in Universal ++= {
  (resourceDirectory in Compile) map { resourceDir: File =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }
  }.value

// Docker
val defaultDockerInstallationPath = "/opt/docker"
mainClass in Compile := Some("codacy.Engine")
packageName in Docker := name.value
maintainer in Docker := "José Melo <jose@codacy.com>"
dockerBaseImage := "library/openjdk:8-jre-alpine"
dockerUpdateLatest := true
defaultLinuxInstallLocation in Docker := defaultDockerInstallationPath
daemonUser in Docker := "docker"
daemonGroup in Docker := "docker"
dockerEntrypoint := Seq(s"$defaultDockerInstallationPath/bin/${name.value}")
dockerCmd := Seq()
dockerCommands := dockerCommands.value.flatMap {
  case cmd@Cmd("ADD", _) =>
    List(
      Cmd("RUN", "adduser -u 2004 -D docker"),
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      ExecCmd("RUN", Seq("chown", "-R", s"docker:docker", "/docs"): _*)
    )
  case other => List(other)
}
