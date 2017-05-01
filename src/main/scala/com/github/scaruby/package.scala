package com.github

import java.util.Locale

import com.github.scaruby.com.github.scaruby.typeclass.CommandAdapter

import scala.language.reflectiveCalls
import scala.sys.process.Process

package object scaruby {
  final val DefaultEncoding: String = System.getProperty("file.encoding")
  final val DefaultLocale: Locale = Locale.getDefault
  def using[A <: { def close(): Unit }, B](resource: A)(block: A => B): B = {
    try {
      block(resource)
    } finally {
      resource.close()
    }
  }
  def system[A:CommandAdapter](command: A, cwd: SFile = SFile("."), extraEnv: Seq[(String, String)] = Seq.empty) = {
    val adapter = implicitly[CommandAdapter[A]]
    Process(adapter.adapt(command), cwd.file, extraEnv:_*).!!
  }
}
