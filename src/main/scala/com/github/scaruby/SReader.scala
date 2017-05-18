package com.github.scaruby

import java.nio.CharBuffer

import scala.collection.immutable.Stream
import scala.collection.mutable

/**
  * An abstract character stream.  After using this object, `close()` method should be called.
  *
  */
abstract class SReader extends SClosableResource[SReader] {
  /**
    * Reads a line from this reader.
    */
  def readLine(): String

  /**
    * Reads one Char from this reader.  If the reader is at the end of file, return -1.
    */
  def read(): Int

  /**
    * Read from this reader and fill into `buffer`.
    * @param buffer an Array of Char
    * @param offset offset of `buffer` used to determine the beginning of `buffer` to be read
    * @param length length of `buffer`used to determine how many characters should be read
    * @return length of characters actually read
    */
  def readInto(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int

  /**
    * Returns Iterator, which one element corresponds one line.
    */
  def linesIterator(): Iterator[String] = {
    Iterator.continually(readLine()).takeWhile(_ != null)
  }

  /**
    * Reads all texts from this reader.
    */
  def readAll(): String = {
    var ch: Int = -1
    val buffer = new java.lang.StringBuilder
    while({ch = read(); ch} != -1) {
      buffer.append(ch.asInstanceOf[Char])
    }
    new String(buffer)
  }

  /**
    * Reads all lines from this reader.
    * @return a sequence of String consisting of lines
    */
  def readLines(): Seq[String] = {
    val lines = mutable.Buffer[String]()
    var line: String = null
    while({line = readLine(); line} != null) {
      lines += line
    }
    lines.toSeq
  }

  /**
    * Closes this reader.  After closing it, any method must not be called.
    */
  def close(): Unit
}
