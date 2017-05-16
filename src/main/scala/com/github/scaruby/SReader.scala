package com.github.scaruby

import java.nio.CharBuffer

import scala.collection.immutable.Stream
import scala.collection.mutable

abstract class SReader[A <: { def close(): Unit}] extends SClosableResource[A] {
  def readLine(): String

  def read(): Int

  def readInto(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int

  def linesIterator(): Iterator[String] = {
    Iterator.continually(readLine()).takeWhile(_ != null)
  }

  def readAll(): String = {
    var ch: Int = -1
    val buffer = new java.lang.StringBuilder
    while({ch = read(); ch} != -1) {
      buffer.append(ch.asInstanceOf[Char])
    }
    new String(buffer)
  }

  def readLines(): Seq[String] = {
    val lines = mutable.Buffer[String]()
    var line: String = null
    while({line = readLine(); line} != null) {
      lines += line
    }
    lines.toSeq
  }

  def close(): Unit
}
