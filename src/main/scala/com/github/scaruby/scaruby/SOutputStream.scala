package com.github.scaruby.scaruby

import java.io.{BufferedOutputStream, FileOutputStream}

class SOutputStream(path: String) {
  private[this] val fileStream = new BufferedOutputStream(new FileOutputStream(path))

  def write(b: Int): Unit = fileStream.write(b)

  def write(b: Array[Byte], off: Int, len: Int): Unit = fileStream.write(b, off, len)

  def flush(): Unit = fileStream.flush()

  def write(b: Array[Byte]): Unit = fileStream.write(b)

  def writeAll(s: String, encoding: String = System.getProperty("file.encoding")): Unit = {
    write(s.getBytes(encoding))
  }

  def close(): Unit = fileStream.close()
}
