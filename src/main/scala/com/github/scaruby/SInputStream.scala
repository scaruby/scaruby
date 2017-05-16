package com.github.scaruby

import scala.collection.mutable

abstract class SInputStream[A <: { def close(): Unit}] extends SClosableResource[A] {
  def readInto(b: Array[Byte])(implicit offset: Int = 0, length: Int = b.length): Int

  def read(): Int

  def readAll(): Array[Byte] = {
    val buffer = mutable.Buffer[Byte]()
    var b: Int = -1
    while({ b = read(); b} != -1) {
      buffer += b.asInstanceOf[Byte]
    }
    buffer.toArray
  }

  def close(): Unit

  def available(): Int
}
