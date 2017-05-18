package com.github.scaruby

import java.io.{BufferedReader, File, FileInputStream, InputStreamReader}
import java.nio.CharBuffer

import scala.collection.mutable
import scala.collection.immutable.Stream

class SFileReader private[scaruby](path: File, encoding: String = DefaultEncoding) extends SReader {
  private[this] val fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))

  def this(path: String, encoding: String) {
    this(new File(path), encoding)
  }

  /**
    * @inheritdoc
    */
  override def self: SFileReader = this

  /**
    * @inheritdoc
    */
  override def readLine(): String = fileReader.readLine()

  /**
    * @inheritdoc
    */
  override def read(): Int = fileReader.read()

  /**
    * @inheritdoc
    */
  override def readInto(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int = fileReader.read(buffer, offset, length)

  /**
    * @inheritdoc
    */
  override def close(): Unit = fileReader.close()
}
