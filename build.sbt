name := "ambient-weather-scala"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "com.lihaoyi" %% "requests" % "0.6.5"

libraryDependencies += "com.lihaoyi" %% "upickle" % "1.2.2"
libraryDependencies += "com.lihaoyi" %% "utest" % "0.7.5" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")