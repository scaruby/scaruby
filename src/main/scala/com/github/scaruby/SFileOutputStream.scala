package com.github.scaruby

import java.io.{BufferedOutputStream, FileOutputStream}

class SFileOutputStream(path: String) extends SOutputStream[SFileOutputStream] {
  private[this] val fileStream = new BufferedOutputStream(new FileOutputStream(path))

  override def self: SFileOutputStream = this

  /**
    * @inheritdoc
    */
  override def write(b: Int): Unit = fileStream.write(b)

  /**
    * @inheritdoc
    */
  override def writeWithOffset(b: Array[Byte], offset: Int, length: Int): Unit = fileStream.write(b, offset, length)

  /**
    * @inheritdoc
    */
  override def flush(): Unit = fileStream.flush()

  /**
    * @inheritdoc
    */
  override def writeAll(b: Array[Byte]): Unit = fileStream.write(b)

  /**
    * @inheritdoc
    */
  override def writeString(s: String, encoding: String = DefaultEncoding): Unit = {
    writeAll(s.getBytes(encoding))
  }

  /**
    * @inheritdoc
    */
  override def close(): Unit = fileStream.close()
}
