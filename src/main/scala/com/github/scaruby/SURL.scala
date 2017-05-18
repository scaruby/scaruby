package com.github.scaruby

import java.io.InputStream
import java.net.URL

class SURL private (val jURL: URL) {
  private class SURLInputStream(inputStream: InputStream) extends SInputStream {

    override def readInto(buffer: Array[Byte])(implicit offset: Int = 0, length: Int = buffer.length): Int = inputStream.read(buffer, offset, length)

    override def read(): Int = inputStream.read()

    override def close(): Unit = inputStream.close()

    override def available(): Int = inputStream.available()

    override def self: SURLInputStream = this
  }

  /**
    * Opens this `SURL`, calls the `block` with opened `SInputStream`, and closes it.
    * @param block called with `SInputStream`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openWith[B](block: SInputStream => B): B = using(new SURLInputStream(jURL.openStream()))(block)

  /**
    * Opens this `SURL` and returns a `SInputStream` bounded to it.
    * @return `SInputStream` bounded to the URL
    */
  def open(): SInputStream = new SURLInputStream(jURL.openStream())
}

object SURL {
  def apply(url: String): SURL = new SURL(new URL(url))
}
