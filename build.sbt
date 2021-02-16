lazy val scala213 = "2.13.4"
lazy val scala212 = "2.12.13"
lazy val supportedScalaVersions = List(scala213, scala212)

name := "ambient-weather-scala"
homepage := Some(url("https://overbeck.org"))
scmInfo := Some(ScmInfo(url("https://github.com/coverbeck/ambient-weather-scala/"), "git@github.com:coverbeck/ambient-weather-scala.git"))
developers := List(Developer("coverbeck", "coverbeck", "charles.overbeck@gmail.com", url("https://overbeck.org")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / organization := "org.overbeck"
ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := scala213

lazy val core = (project in file("."))
  .settings(
      crossScalaVersions := supportedScalaVersions
  )

//crossPaths := false

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

scalaVersion := "2.13.4"

libraryDependencies += "com.lihaoyi" %% "requests" % "0.6.5"
libraryDependencies += "com.lihaoyi" %% "upickle" % "1.2.2"
libraryDependencies += "com.lihaoyi" %% "utest" % "0.7.5" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")