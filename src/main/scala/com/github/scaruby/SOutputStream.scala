package com.github.scaruby

/**
  * An object to write bytes to a stream
  */
abstract class SOutputStream extends SClosableResource[SOutputStream] {
  /**
    * Write one byte from `byte`
    * @param byte to be written
    */
  def write(byte: Int): Unit

  /**
    * Writes `buffer` using `offset` and `length`
    * @param buffer to be written
    * @param offset start position
    * @param length length
    */
  def writeWithOffset(buffer: Array[Byte], offset: Int, length: Int): Unit

  /**
    * Flushes this stream.
    */
  def flush(): Unit

  /**
    * Writes all bytes from the `input`
    * @param buffer input
    */
  def writeAll(buffer: Array[Byte]): Unit

  /**
    * Writes a String `string` as `encoding`.  if `encoding` is omitted, the default encoding is used.
    * @param string to be written
    * @param encoding be used to write
    */
  def writeString(string: String, encoding: String = DefaultEncoding): Unit

  /**
    * Closes this stream.  After closing this stream, any method must not be called.
    */
  def close(): Unit
}
