package com.github.scaruby

import java.io.{BufferedReader, InputStream, InputStreamReader, Reader}
import java.net.URL

class SURL private (val jURL: URL) {
  private class SURLInputStream(inputStream: InputStream) extends SInputStream {

    override def readInto(buffer: Array[Byte])(implicit offset: Int = 0, length: Int = buffer.length): Int = inputStream.read(buffer, offset, length)

    override def read(): Int = inputStream.read()

    override def close(): Unit = inputStream.close()

    override def available(): Int = inputStream.available()

    override def self: SURLInputStream = this
  }

  private class SURLReader(reader: BufferedReader) extends SReader{

    override def readLine(): String = reader.readLine()

    override def read(): Int = reader.read()

    override def readInto(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Int = {
      reader.read(buffer, offset, length)
    }

    override def close(): Unit = reader.close()

    override def self: SURLReader = this
  }

  /**
    * Read all texts from this file
    * @return content
    */
  def read(): String = for {
    reader <- this.openReader()
  } reader.readAll()

  /**
    * Read lines from this file
    * @return a sequence of lines
    */
  def readLines(): Seq[String] = for {
    reader <- this.openReader()
  } reader.readLines()

  /**
    * Opens this `SURL`, calls the `block` with opened `SInputStream`, and closes it.
    * @param block called with `SInputStream`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openInputStreamWith[B](block: SInputStream => B): B = using(new SURLInputStream(jURL.openStream()))(block)

  /**
    * Opens this `SURL` and returns a `SInputStream` bounded to it.
    * @return `SInputStream` bounded to the URL
    */
  def openInputStream(): SInputStream = new SURLInputStream(jURL.openStream())

  /**
    * Opens this `SURL`, calls the `block` with opened `SReader`, and closes it.
    * @param block called with `SReader`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openReaderWith[B](block: SReader => B): B = {
    using(new SURLReader(new BufferedReader(new InputStreamReader((jURL.openStream())))))(block)
  }

  /**
    * Opens this `SURL` and returns a `SReader` bounded to it.
    * @return `SReader` bounded to the URL
    */
  def openReader(): SReader = new SURLReader(new BufferedReader(new InputStreamReader(jURL.openStream())))
}

object SURL {
  def apply(url: String): SURL = new SURL(new URL(url))
}
