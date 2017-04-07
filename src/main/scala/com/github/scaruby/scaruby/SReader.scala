package com.github.scaruby.scaruby

import java.io.{BufferedReader, FileInputStream, InputStreamReader}
import java.nio.CharBuffer
import java.util.stream

class SReader(path: String, encoding: String = DefaultEncoding) {
  private[this] val fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))

  def reset(): Unit = fileReader.reset()

  def readLine(): String = fileReader.readLine()

  def lines(): stream.Stream[String] = fileReader.lines()

  def read(): Int = fileReader.read()

  def markSupported(): Boolean = fileReader.markSupported()

  def skip(n: Long): Long = fileReader.skip(n)

  def ready(): Boolean = fileReader.ready()

  def mark(readAheadLimit: Int): Unit = fileReader.mark(readAheadLimit)

  def read(cbuf: Array[Char], off: Int, len: Int): Int = fileReader.read(cbuf, off, len)

  def close(): Unit = fileReader.close()

  def read(target: CharBuffer): Int = fileReader.read(target)

  def read(cbuf: Array[Char]): Int = fileReader.read(cbuf)

  def readAll(): String = {
    var ch: Int = -1
    val buffer = new java.lang.StringBuilder
    while({ch = read(); ch} != -1) {
      buffer.append(ch)
    }
    new String(buffer)
  }
}
