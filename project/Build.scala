import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import playscalajs.PlayScalaJS

object ApplicationBuild extends Build {

  override def rootProject = Some(jvm)

  val example = PlayScalaJS("example", file(".")).
    jvmSettings(
      libraryDependencies ++= Dependencies.jvm.value
    ).jsSettings(
      libraryDependencies ++= Dependencies.js.value
    ).settings(
      scalaVersion := Versions.scala,
      libraryDependencies ++= Dependencies.shared.value,

      libraryDependencies ++= Seq(
          "com.lihaoyi" %%% "utest" % "0.2.5-M3" % "test"
      ),
      testFrameworks += new TestFramework("utest.runner.Framework")
    )

  lazy val jvm = example.jvm
  lazy val js = example.js

  // Only if you use IntelliJ: the shared project makes IntelliJ happy without using symlinks
  lazy val shared = Project("exampleShared", file("shared"))
}

object Dependencies {
  val shared = Def.setting(Seq())

  val jvm = Def.setting(Seq(
    "com.vmunier" %% "play-scalajs-sourcemaps" % Versions.playScalajsSourcemaps,
    "org.webjars" % "jquery" % Versions.jquery
  ))

  val js = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % Versions.scalajsDom
  ))
}

object Versions {
  val app = "0.1.0-SNAPSHOT"
  val scala = "2.11.2"
  val scalajsDom = "0.7.0"
  val jquery = "1.11.1"
  val playScalajsSourcemaps = "0.1.0"
}
