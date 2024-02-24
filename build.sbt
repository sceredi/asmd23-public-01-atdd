ThisBuild / version := "0.1.0-SNAPSHOT"
scalaVersion := "3.3.1"
lazy val root = (project in file("."))
  .settings(
    name := "java-sbt-example",
    libraryDependencies ++= Seq(
      "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
      "io.cucumber" % "cucumber-java" % "7.15.0" % Test,
      "io.cucumber" %% "cucumber-scala" % "8.20.0" % Test,
    )
  )
