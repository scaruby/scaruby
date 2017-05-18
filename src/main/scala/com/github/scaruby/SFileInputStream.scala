package com.github.scaruby

import java.io.{BufferedInputStream, FileInputStream}

import scala.collection.mutable

class SFileInputStream(path: String) extends SInputStream {
  private[this] val fileStream: BufferedInputStream = new BufferedInputStream(new FileInputStream(path))

  override def self: SFileInputStream = this

  /**
    * @inheritdoc
    */
  override def readInto(b: Array[Byte])(implicit offset: Int = 0, length: Int = b.length): Int = fileStream.read(b)

  /**
    * @inheritdoc
    */
  override def read(): Int = fileStream.read()

  /**
    * @inheritdoc
    */
  override def close(): Unit = fileStream.close()

  /**
    * @inheritdoc
    */
  override def available(): Int = fileStream.available()
}
