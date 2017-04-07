package com.github.scaruby.scaruby

import java.io.{BufferedInputStream, FileInputStream}

import scala.collection.mutable

class SInputStream(path: String) {
  private[this] val fileStream: BufferedInputStream = new BufferedInputStream(new FileInputStream(path))

  def reset(): Unit = fileStream.reset()

  def mark(readlimit: Int): Unit = fileStream.mark(readlimit)

  def skip(n: Long): Long = fileStream.skip(n)

  def read(b: Array[Byte], off: Int, len: Int): Int = fileStream.read(b, off, len)

  def markSupported(): Boolean = fileStream.markSupported()

  def close(): Unit = fileStream.close()

  def available(): Int = fileStream.available()

  def read(): Int = fileStream.read()

  def read(b: Array[Byte]): Int = fileStream.read(b)

  def readAll(): Array[Byte] = {
    val buffer = mutable.Buffer[Byte]()
    var b: Int = -1
    while({ b = read(); b} != -1) {
      buffer += b.asInstanceOf[Byte]
    }
    buffer.toArray
  }
}
