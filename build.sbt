import sjsonnew._
import sjsonnew.BasicJsonProtocol._
import sjsonnew.support.scalajson.unsafe._
import java.nio.file.Files
import scala.collection.JavaConverters._
import com.typesafe.sbt.packager.Keys.{
  daemonUser,
  defaultLinuxInstallLocation,
  dockerAlias,
  dockerBaseImage,
  dockerCmd,
  dockerEntrypoint,
  dockerUpdateLatest,
  maintainer,
  packageName
}
import com.typesafe.sbt.packager.docker.{Cmd, DockerAlias, ExecCmd}


organization := "com.codacy"

name := "codacy-codenarc"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0",
  "org.specs2" %% "specs2-core" % "4.6.0" % Test
)

scalacOptions.in(Compile, console) --= Seq("-Ywarn-unused",
  "-Ywarn-unused:imports",
  "-Xfatal-warnings")
scalacOptions in Test ++= Seq("-Yrangepos")

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin)

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

// write codenarc version into .codenarc-version
lazy val codeNarcVersionFile = Def.setting {
  val f = file(".codenarc-version")
  IO.write(f, toolVersion.value)
  f
}

mappings.in(Universal) ++= resourceDirectory
  .in(Compile)
  .zip(codeNarcVersionFile)
  .map {
    case (resourceDir: File, versionFile: File) =>
      val src = resourceDir / "docs"
      val dest = "/docs"

      val docFiles = {
        val res = for {
          path <- Files.walk(src.toPath).iterator().asScala
          if !Files.isDirectory(path)
        } yield path.toFile -> path.toString.replaceFirst(src.toString, dest)
        res.toSeq
      }

      docFiles
  }
  .value

val groovyVersion = "2.5.8"

def installGroovy() =
  s"""apk upgrade --update &&
    |    apk add --no-cache --virtual=build-dependencies curl ca-certificates wget unzip &&
    |    wget -q -O /tmp/groovy.zip "https://dl.bintray.com/groovy/maven/apache-groovy-binary-$groovyVersion.zip" &&
    |    unzip -o -d "/tmp" "/tmp/groovy.zip" &&
    |    mv "/tmp/groovy-$groovyVersion" "/usr/share/groovy" &&
    |    apk del build-dependencies &&
    |    rm -rf /tmp/* /var/cache/apk/*""".stripMargin.replaceAll(System.lineSeparator(), " ")


// Docker
val defaultDockerInstallationPath = "/opt/docker"
mainClass in Compile := Some("codacy.Engine")
packageName in Docker := name.value
version in Docker := version.value
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
      Cmd("ENV", s"TOOL_VERSION ${toolVersion.value}"),
      Cmd("ENV", s"GROOVY_VERSION $groovyVersion"),
      cmd,
      Cmd("RUN", installGroovy()),
      Cmd("ENV", "GROOVY_HOME /usr/share/groovy"),
      Cmd("ENV", "PATH $PATH:$GROOVY_HOME/bin"),
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      ExecCmd("RUN", Seq("chown", "-R", s"docker:docker", "/docs"): _*))
  case other => List(other)
}
