package com.github.scaruby

import java.io.InputStream
import java.net.URL

class SURL private (val jURL: URL) {
  private class SURLInputStream(inputStream: InputStream) extends SInputStream[SURLInputStream] {

    override def readInto(buffer: Array[Byte])(implicit offset: Int = 0, length: Int = buffer.length): Int = inputStream.read(buffer, offset, length)

    override def read(): Int = inputStream.read()

    override def close(): Unit = inputStream.close()

    override def available(): Int = inputStream.available()

    override def self: SURLInputStream = this
  }
}

object SURL {
  def apply(url: String): SURL = new SURL(new URL(url))
}
