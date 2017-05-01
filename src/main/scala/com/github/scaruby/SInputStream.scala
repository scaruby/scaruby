package com.github.scaruby

import scala.collection.mutable

abstract class SInputStream[A <: { def close(): Unit}] extends SClosableResource[A] {
  def reset(): Unit

  def mark(limit: Int): Unit

  def skip(n: Long): Long

  def readTo(b: Array[Byte]): Int

  def readToWithOffset(b: Array[Byte], offset: Int, length: Int): Int

  def read(): Int

  def readAll(): Array[Byte] = {
    val buffer = mutable.Buffer[Byte]()
    var b: Int = -1
    while({ b = read(); b} != -1) {
      buffer += b.asInstanceOf[Byte]
    }
    buffer.toArray
  }

  def markSupported(): Boolean

  def close(): Unit

  def available(): Int
}
