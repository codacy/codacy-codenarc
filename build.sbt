import sjsonnew._
import sjsonnew.BasicJsonProtocol._
import sjsonnew.support.scalajson.unsafe._
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
lazy val toolVersion = settingKey[String]("The version of the underlying tool")
toolVersion := {
  val version = IO.readLines(new File(".codenarc-version"))
  version.mkString("")
}

libraryDependencies ++= Seq(
  "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0",
  "org.codenarc" % "CodeNarc" % toolVersion.value,
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "org.slf4j" % "slf4j-nop" % "1.7.12",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)

scalacOptions.in(Compile, console) --= Seq("-Ywarn-unused", "-Ywarn-unused:imports", "-Xfatal-warnings")
scalacOptions in Test ++= Seq("-Yrangepos")

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
enablePlugins(AshScriptPlugin)

resourceDirectory in IntegrationTest := (baseDirectory apply { baseDir: File =>
  baseDir / "test/resources"
}).value

mappings in Universal ++=
  (resourceDirectory in Compile).map {
    case (resourceDir: File) =>
      val src = resourceDir / "docs"
      val dest = "/docs"

      val docFiles = for {
        path <- src.allPaths.get if !path.isDirectory
      } yield path -> path.toString.replaceFirst(src.toString, dest)

      docFiles
  }.value

// Docker
mainClass in Compile := Some("codacy.Engine")
packageName in Docker := name.value
maintainer in Docker := "José Melo <jose@codacy.com>"
dockerBaseImage := "library/openjdk:8-jre-alpine"
dockerUpdateLatest := true
defaultLinuxInstallLocation in Docker := Constants.defaultDockerInstallationPath
daemonUser in Docker := Constants.dockerUser
daemonGroup in Docker := Constants.dockerUser
dockerEntrypoint := Seq(s"${Constants.defaultDockerInstallationPath}/bin/${name.value}")
dockerCmd := Seq()
dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(
      Cmd("RUN", s"adduser -u 2004 -D ${Constants.dockerUser}"),
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      ExecCmd("RUN", Seq("chown", "-R", s"${Constants.dockerUser}:${Constants.dockerUser}", "/docs"): _*)
    )
  case other => List(other)
}
