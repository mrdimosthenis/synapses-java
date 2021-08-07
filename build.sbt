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

val circeVersion = "0.14.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "synapses-java",

    libraryDependencies ++= Seq(
      "com.github.mrdimosthenis" % "synapses_3" % "8.0.0",
      "junit" % "junit" % "4.13.2" % "test",
      "org.apache.commons" % "commons-csv" % "1.9.0" % "test",
      "com.google.guava" % "guava" % "30.1.1-jre" % "test",
      "org.apache.commons" % "commons-lang3" % "3.12.0" % "test"
    ),

    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),

    crossPaths := false,
    autoScalaLibrary := false
  )
