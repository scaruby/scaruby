package com.github.scaruby

abstract class SOutputStream[A <: { def close(): Unit}] extends SClosableResource[A] {
  def write(b: Int): Unit

  def writeWithOffset(b: Array[Byte], offset: Int, length: Int): Unit

  def flush(): Unit

  def writeAll(b: Array[Byte]): Unit

  def writeString(s: String, encoding: String = DefaultEncoding): Unit

  def close(): Unit
}
