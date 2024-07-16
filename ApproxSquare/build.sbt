// See README.md for license details.

ThisBuild / scalaVersion     := "2.12.14"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.dingj3jiangding"

val chiselVersion = "6.2.0"

lazy val root = (project in file("."))
  .settings(
    name := "MyChisel",
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel3" % "3.5.3",
      "org.scalatest" %% "scalatest" % "3.2.16" % "test",
      "edu.berkeley.cs" %% "chiseltest" % "0.5.0" % "test",
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
    ),
    addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.5.3" cross CrossVersion.full),
  )
