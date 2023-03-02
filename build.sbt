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
  scalaVersion := "2.13.10",
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
    "com.codacy" %% "codacy-engine-scala-seed" % "6.1.0"
  )
)

lazy val docGenerator = (project in file("docgenerator"))
  .settings(
    commonSettings,
    name := "docgenerator",
    moduleName := "docgenerator",
    libraryDependencies ++= Seq(
      "org.codenarc" % "CodeNarc" % toolVersion.value,
      "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
      "org.reflections" % "reflections" % "0.10.2",
      "com.github.pathikrit" %% "better-files" % "3.9.2"
    )
  )

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "codacy-codenarc",
    moduleName := "codacy-codenarc",
    libraryDependencies ++= Seq("org.slf4j" % "slf4j-nop" % "2.0.5", "org.scalatest" %% "scalatest" % "3.2.15" % Test)
  )
  .enablePlugins(DockerPlugin)
  .enablePlugins(AshScriptPlugin)

enablePlugins(JavaAppPackaging)

Universal / mappings ++=
  (Compile / resourceDirectory).map { resourceDir =>
    val src = resourceDir / "docs"
    val dest = "/docs"

    for {
      path <- src.allPaths.get if !path.isDirectory
    } yield path -> path.toString.replaceFirst(src.toString, dest)
  }.value

val defaultDockerInstallationPath = "/opt/docker"
val dockerUser = "docker"

// Docker
Compile / mainClass := Some("codacy.Engine")
Docker / packageName := name.value
dockerBaseImage := "amazoncorretto:8-alpine3.17-jre"
dockerUpdateLatest := true
Docker / defaultLinuxInstallLocation := defaultDockerInstallationPath
Docker / daemonUser := dockerUser
Docker / daemonGroup := dockerUser
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
