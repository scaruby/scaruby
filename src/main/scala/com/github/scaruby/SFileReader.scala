package com.github.scaruby

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.CharBuffer

import scala.collection.mutable
import scala.collection.immutable.Stream

class SFileReader(path: String, encoding: String = DefaultEncoding) extends SReader[SFileReader] {
  private[this] val fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))

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
