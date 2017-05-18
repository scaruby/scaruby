package com.github.scaruby

import scala.collection.mutable

abstract class SInputStream extends SClosableResource[SInputStream] {
  def readInto(b: Array[Byte])(implicit offset: Int = 0, length: Int = b.length): Int

  /**
    * Reads one byte from this stream.  Returns -1 at EOF.
    * @return
    */
  def read(): Int

  /**
    * Reads all bytes from this stream and returns them as `Array[Byte]`.
    * @return
    */
  def readAll(): Array[Byte] = {
    val buffer = mutable.Buffer[Byte]()
    var b: Int = -1
    while({ b = read(); b} != -1) {
      buffer += b.asInstanceOf[Byte]
    }
    buffer.toArray
  }

  /**
    * Closes this stream.  After closing this stream, any method must not be called.
    */
  def close(): Unit

  /**
    * Returns currently available bytes.
    */
  def available(): Int
}
