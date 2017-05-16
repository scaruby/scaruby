package com.github.scaruby

import java.io.{BufferedInputStream, FileInputStream}

import scala.collection.mutable

class SFileInputStream(path: String) extends SInputStream[SFileInputStream] {
  private[this] val fileStream: BufferedInputStream = new BufferedInputStream(new FileInputStream(path))

  override def self: SFileInputStream = this

  override def readInto(b: Array[Byte])(implicit offset: Int = 0, length: Int = b.length): Int = fileStream.read(b)

  override def read(): Int = fileStream.read()

  override def close(): Unit = fileStream.close()

  override def available(): Int = fileStream.available()
}
