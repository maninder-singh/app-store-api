name := """app-store-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "mysql" % "mysql-connector-java" % "5.1.27"
)

resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.sun-opsys" % "api-doc" % "0.9.15"
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.4.0-1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
