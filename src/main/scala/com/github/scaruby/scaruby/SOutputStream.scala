package com.github.scaruby.scaruby

import java.io.{BufferedOutputStream, FileOutputStream}

class SOutputStream(path: String) {
  private[this] val fileStream = new BufferedOutputStream(new FileOutputStream(path))

  def write(b: Int): Unit = fileStream.write(b)

  def writeWithOffset(b: Array[Byte], offset: Int, length: Int): Unit = fileStream.write(b, offset, length)

  def flush(): Unit = fileStream.flush()

  def writeAll(b: Array[Byte]): Unit = fileStream.write(b)

  def writeString(s: String, encoding: String = DefaultEncoding): Unit = {
    writeAll(s.getBytes(encoding))
  }

  def close(): Unit = fileStream.close()
}
