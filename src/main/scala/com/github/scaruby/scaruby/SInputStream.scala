package com.github.scaruby.scaruby

import java.io.{BufferedInputStream, FileInputStream}

import scala.collection.mutable

class SInputStream(path: String) extends SClosableResource[SInputStream] {
  override def self: SInputStream = this

  private[this] val fileStream: BufferedInputStream = new BufferedInputStream(new FileInputStream(path))

  def reset(): Unit = fileStream.reset()

  def mark(limit: Int): Unit = fileStream.mark(limit)

  def skip(n: Long): Long = fileStream.skip(n)

  def readWithOffset(b: Array[Byte], offset: Int, length: Int): Int = fileStream.read(b, offset, length)

  def read(): Int = fileStream.read()

  def readTo(b: Array[Byte]): Int = fileStream.read(b)

  def readAll(): Array[Byte] = {
    val buffer = mutable.Buffer[Byte]()
    var b: Int = -1
    while({ b = read(); b} != -1) {
      buffer += b.asInstanceOf[Byte]
    }
    buffer.toArray
  }

  def markSupported(): Boolean = fileStream.markSupported()

  def close(): Unit = fileStream.close()

  def available(): Int = fileStream.available()
}
