organization := "com.codacy"

name := "codacy-codenarc"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.codacy" %% "codacy-engine-scala-seed" % "3.1.0",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0",
  "org.reflections" % "reflections" % "0.9.10",
  "org.codenarc" % "CodeNarc" % "1.5",
  "org.scalatest" % "scalatest_2.12" % "3.1.0" % Test
)
