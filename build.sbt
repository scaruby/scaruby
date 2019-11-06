organization := "com.github.scaruby"

name := "scaruby"

def Scala211 = "2.11.11"
def Scala212 = "2.12.8"
def Scala213 = "2.13.0"
scalaVersion := Scala213

crossScalaVersions := Seq(Scala211, Scala212, Scala213)

publishMavenStyle := true

val scaladocBranch = settingKey[String]("branch name for scaladoc -doc-source-url")

scaladocBranch := "master"

scalacOptions in (Compile, doc) ++= { Seq(
  "-sourcepath", baseDirectory.value.getAbsolutePath,
  "-doc-source-url", s"https://github.com/scaruby/scaruby/tree/${scaladocBranch.value}€{FILE_PATH}.scala"
)}

testOptions in Test += Tests.Argument("-u", "target/scalatest-reports")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)


initialCommands in console += {
  Iterator("com.github.scaruby._").map("import "+).mkString("\n")
}

publishTo := {
  val v = version.value
  val nexus = "https://oss.sonatype.org/"
  if (v.endsWith("-SNAPSHOT"))
    Some("snapshots" at nexus+"content/repositories/snapshots")
  else
    Some("releases" at nexus+"service/local/staging/deploy/maven2")
}

credentials ++= {
  val sonatype = ("Sonatype Nexus Repository Manager", "oss.sonatype.org")
  def loadMavenCredentials(file: java.io.File) : Seq[Credentials] = {
    xml.XML.loadFile(file) \ "servers" \ "server" map (s => {
      val host = (s \ "id").text
      val realm = if (host == sonatype._2) sonatype._1 else "Unknown"
      Credentials(realm, host, (s \ "username").text, (s \ "password").text)
    })
  }
  val ivyCredentials   = Path.userHome / ".ivy2" / ".credentials"
  val mavenCredentials = Path.userHome / ".m2"   / "settings.xml"
  (ivyCredentials.asFile, mavenCredentials.asFile) match {
    case (ivy, _) if ivy.canRead => Credentials(ivy) :: Nil
    case (_, mvn) if mvn.canRead => loadMavenCredentials(mvn)
    case _ => Nil
  }
}
