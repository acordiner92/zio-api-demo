val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Api",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "io.d11" %% "zhttp" % "2.0.0-RC2",
      "dev.zio" %% "zio-json" % "0.3.0-RC2"
    )
  )
