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

// Tool version
lazy val toolVersion = settingKey[String]("The version of the underlying tool")
ThisBuild / toolVersion := "3.2.0"

lazy val commonSettings = Seq(
  organization := "com.codacy",
  version := "1.0.0-SNAPSHOT",
  scalaVersion := "2.13.1",
  Compile / sourceGenerators += Def.task {
    val file = (Compile / sourceManaged).value / "codacy" / "codenarc" / "docs" / "Versions.scala"
    IO.write(file, s"""package codacy.codenarc.docs 
                      |object Versions {
                      |  val codenarcVersion: String = "${toolVersion.value}"
                      |}
                      |""".stripMargin)
    Seq(file)
  }.taskValue,
  libraryDependencies ++= Seq(
    "org.codenarc" % "CodeNarc" % toolVersion.value,
    "com.codacy" %% "codacy-engine-scala-seed" % "5.0.1"
  )
)

lazy val docGenerator = (project in file("docgenerator"))
  .settings(
    commonSettings,
    name := "docgenerator",
    moduleName := "docgenerator",
    libraryDependencies ++= Seq(
      "org.codenarc" % "CodeNarc" % toolVersion.value,
      "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
      "org.reflections" % "reflections" % "0.10.2"
    )
  )

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "codacy-codenarc",
    moduleName := "codacy-codenarc",
    libraryDependencies ++= Seq("org.slf4j" % "slf4j-nop" % "1.7.12", "org.scalatest" %% "scalatest" % "3.1.0" % Test)
  )
  .enablePlugins(DockerPlugin)
  .enablePlugins(AshScriptPlugin)

enablePlugins(JavaAppPackaging)

resourceDirectory in IntegrationTest := baseDirectory.value / "test" / "resources"

mappings in Universal ++=
  (resourceDirectory in Compile).map { resourceDir =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }.value

val defaultDockerInstallationPath = "/opt/docker"
val dockerUser = "docker"

// Docker
mainClass in Compile := Some("codacy.Engine")
packageName in Docker := name.value
maintainer in Docker := "José Melo <jose@codacy.com>"
dockerBaseImage := "amazoncorretto:8-alpine3.14-jre"
dockerUpdateLatest := true
defaultLinuxInstallLocation in Docker := defaultDockerInstallationPath
daemonUser in Docker := dockerUser
daemonGroup in Docker := dockerUser
dockerEntrypoint := Seq(s"$defaultDockerInstallationPath/bin/${name.value}")
dockerCmd := Seq()
dockerCommands := dockerCommands.value.flatMap {
  case cmd @ Cmd("ADD", _) =>
    List(
      Cmd("RUN", s"adduser -u 2004 -D $dockerUser"),
      cmd,
      Cmd("RUN", "mv /opt/docker/docs /docs"),
      ExecCmd("RUN", "chown", "-R", s"$dockerUser:$dockerUser", "/docs")
    )
  case other => List(other)
}
