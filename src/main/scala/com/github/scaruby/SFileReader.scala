package com.github.scaruby

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.CharBuffer

import scala.collection.mutable
import scala.collection.immutable.Stream

class SFileReader(path: String, encoding: String = DefaultEncoding) extends SReader[SFileReader] {
  private[this] val fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))

  override def self: SFileReader = this

  override def reset(): Unit = fileReader.reset()

  override def readLine(): String = fileReader.readLine()

  override def read(): Int = fileReader.read()

  override def readIntoWithOffset(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int = fileReader.read(buffer, offset, length)

  override def readInto(buffer: CharBuffer): Int = fileReader.read(buffer)

  override def markSupported(): Boolean = fileReader.markSupported()

  override def skip(n: Long): Long = fileReader.skip(n)

  override def ready(): Boolean = fileReader.ready()

  override def mark(readAheadLimit: Int): Unit = fileReader.mark(readAheadLimit)

  override def close(): Unit = fileReader.close()
}
