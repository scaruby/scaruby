package com.github.scaruby

import java.io.{BufferedOutputStream, File, FileOutputStream}

class SFileOutputStream private[scaruby](path: File) extends SOutputStream {
  private[this] val fileStream = new BufferedOutputStream(new FileOutputStream(path))

  def this(path: String) {
    this(new File(path))
  }

  override def self: SFileOutputStream = this

  /**
    * @inheritdoc
    */
  override def write(b: Int): Unit = fileStream.write(b)

  /**
    * @inheritdoc
    */
  override def writeWith(buffer: Array[Byte])(implicit offset: Int = 0, length: Int = buffer.length): Unit = {
    fileStream.write(buffer, offset, length)
  }

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
