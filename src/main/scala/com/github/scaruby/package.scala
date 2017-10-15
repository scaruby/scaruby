package com.github

import java.util.Locale

import com.github.scaruby.typeclass.CommandSequenceFactory

import scala.language.reflectiveCalls
import scala.sys.process.Process

package object scaruby {
  final val DefaultEncoding: String = "UTF-8"
  final val DefaultLocale: Locale = Locale.getDefault
  def using[A <: { def close(): Unit }, B](resource: A)(block: A => B): B = {
    try {
      block(resource)
    } finally {
      resource.close()
    }
  }
  def system[A:CommandSequenceFactory](command: A, cwd: SFile = SFile("."), extraEnv: Seq[(String, String)] = Seq.empty): String = {
    val adapter = implicitly[CommandSequenceFactory[A]]
    Process(adapter.newInstanceFrom(command), cwd.file, extraEnv:_*).!!
  }

}
