package com.github.scaruby

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.CharBuffer

import scala.collection.mutable
import scala.collection.immutable.Stream

class SFileReader(path: String, encoding: String = DefaultEncoding) extends SClosableResource[SFileReader] {
  private[this] val fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))

  override def self: SFileReader = this

  def reset(): Unit = fileReader.reset()

  def readLine(): String = fileReader.readLine()

  def lines(): Seq[String] = {
    Stream.continually(readLine()).takeWhile(_ != null)
  }

  def linesIterator(): Iterator[String] = {
    Iterator.continually(readLine()).takeWhile(_ != null)
  }

  def read(): Int = fileReader.read()

  def readWithOffset(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int = fileReader.read(buffer, offset, length)

  def read(buffer: CharBuffer): Int = fileReader.read(buffer)

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

  def markSupported(): Boolean = fileReader.markSupported()

  def skip(n: Long): Long = fileReader.skip(n)

  def ready(): Boolean = fileReader.ready()

  def mark(readAheadLimit: Int): Unit = fileReader.mark(readAheadLimit)

  def close(): Unit = fileReader.close()

}
