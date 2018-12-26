name := "applicationlayer"
 
version := "1.0" 
      
lazy val `applicationlayer` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies += filters

libraryDependencies ++= Seq( javaJdbc , cache , javaWs, "org.mongodb.morphia" % "morphia" % "1.2.1",  "org.modelmapper" % "modelmapper" % "2.3.0" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

      