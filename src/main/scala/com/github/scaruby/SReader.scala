package com.github.scaruby

import java.nio.CharBuffer

import scala.collection.immutable.Stream
import scala.collection.mutable

abstract class SReader[A <: { def close(): Unit}] extends SClosableResource[A] {
  def reset(): Unit

  def readLine(): String

  def read(): Int

  def readToWithOffset(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int

  def readTo(buffer: CharBuffer): Int

  def lines(): Seq[String] = {
    Stream.continually(readLine()).takeWhile(_ != null).toVector
  }

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

  def markSupported(): Boolean

  def skip(n: Long): Long

  def ready(): Boolean

  def mark(readAheadLimit: Int): Unit

  def close(): Unit
}
