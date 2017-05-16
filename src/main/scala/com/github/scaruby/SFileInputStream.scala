package com.github.scaruby

import java.io.{BufferedInputStream, FileInputStream}

import scala.collection.mutable

class SFileInputStream(path: String) extends SInputStream[SFileInputStream] {
  private[this] val fileStream: BufferedInputStream = new BufferedInputStream(new FileInputStream(path))

  override def self: SFileInputStream = this

  override def reset(): Unit = fileStream.reset()

  override def mark(limit: Int): Unit = fileStream.mark(limit)

  override def skip(n: Long): Long = fileStream.skip(n)

  override def readInto(b: Array[Byte]): Int = fileStream.read(b)

  override def readIntoWithOffset(b: Array[Byte], offset: Int, length: Int): Int = fileStream.read(b, offset, length)

  override def read(): Int = fileStream.read()

  override def markSupported(): Boolean = fileStream.markSupported()

  override def close(): Unit = fileStream.close()

  override def available(): Int = fileStream.available()
}
