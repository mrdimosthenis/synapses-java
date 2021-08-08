inThisBuild(List(
  organization := "com.github.mrdimosthenis",
  homepage := Some(url("https://github.com/mrdimosthenis/Synapses")),
  licenses := List("MIT" -> url("https://rem.mit-license.org/")),
  developers := List(
    Developer(
      "mrdimosthenis",
      "Dimos Michailidis",
      "mrdimosthenis@hotmail.com",
      url("https://github.com/mrdimosthenis")
    )
  )
))

val scala3Version = "3.0.1"

val circeVersion = "0.14.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "synapses-java",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.github.mrdimosthenis" % "synapses_3" % "8.0.0",
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.apache.commons" % "commons-csv" % "1.9.0" % "test",
      "com.google.guava" % "guava" % "30.1.1-jre" % "test",
      "org.apache.commons" % "commons-lang3" % "3.12.0" % "test"
    ),

    crossPaths := false,
    autoScalaLibrary := false
  )
