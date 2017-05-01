package com.github.scaruby

import java.io.{BufferedOutputStream, FileOutputStream}

class SFileOutputStream(path: String) extends SOutputStream[SFileOutputStream] {
  private[this] val fileStream = new BufferedOutputStream(new FileOutputStream(path))

  override def self: SFileOutputStream = this

  override def write(b: Int): Unit = fileStream.write(b)

  override def writeWithOffset(b: Array[Byte], offset: Int, length: Int): Unit = fileStream.write(b, offset, length)

  override def flush(): Unit = fileStream.flush()

  override def writeAll(b: Array[Byte]): Unit = fileStream.write(b)

  override def writeString(s: String, encoding: String = DefaultEncoding): Unit = {
    writeAll(s.getBytes(encoding))
  }

  override def close(): Unit = fileStream.close()
}
