# Scaruby: Ruby-like lightweight scripting library for Scala 

[![Join the chat at https://gitter.im/scaruby/scaruby](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/scaruby/scaruby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/scaruby/scaruby.png?branch=master)](https://travis-ci.org/scaruby/scaruby)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.scaruby/scaruby_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.scaruby/scaruby_2.11)
[![Scaladoc](http://javadoc-badge.appspot.com/com.github.scaruby/scaruby_2.11.svg?label=scaladoc)](http://javadoc-badge.appspot.com/com.github.scaruby/scaruby_2.11/index.html#com.github.scaruby.package)
[![Reference Status](https://www.versioneye.com/java/com.github.scaruby:scaruby_2.11/reference_badge.svg?style=flat)](https://www.versioneye.com/java/com.github.scaruby:scaruby_2.11/references)


Scaruby is not a thin layer of `java.io` but a thick layer.  Users don't need to know the detail of `java.io`.

## Usage

Now Scaruby supports Scala 2.11.X and Scala 2.12.X.

Add the following lines to your build.sbt:

```scala
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependency += "com.github.scaruby" %% "scaruby" % "0.1"
```

and you can use Scaruby like the following after importing the package `com.github.scaruby._`:

```scala
import com.github.scaruby._

val content = SFile.read("file.txt")
```

```scala
import com.github.scaruby._

val input = "Hello, World!"
val encoded = SBase64.encode64(input.getBytes("UTF-8"))
val decoded = new String(SBase64.decode64(encoded), "UTF-8")
assert(input == decoded)
```
