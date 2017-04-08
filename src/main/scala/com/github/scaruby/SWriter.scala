package com.github.scaruby

import java.io.{FileOutputStream, OutputStreamWriter, PrintWriter}
import java.util.Locale

class SWriter(path: String, encoding: String = DefaultEncoding) extends SClosableResource[SWriter] {
  private[this] val fileWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), encoding))

  override def self: SWriter = this

  def hasError(): Boolean = fileWriter.checkError()

  def close(): Unit = fileWriter.close()

  def write(buf: Array[Char]): Unit = fileWriter.write(buf)

  def writeWith(buf: Array[Char], off: Int, len: Int): Unit = fileWriter.write(buf, off, len)

  def writeChar(c: Int): Unit = fileWriter.write(c)

  def writeString(s: String): Unit = fileWriter.write(s)

  def writeStringWith(s: String, off: Int, len: Int): Unit = fileWriter.write(s, off, len)

  def format(l: Locale, format: String, args: AnyRef*): PrintWriter = fileWriter.format(l, format, args)

  def printf(format: String, args: AnyRef*): PrintWriter = fileWriter.printf(format, args)

  def format(format: String, args: AnyRef*): PrintWriter = fileWriter.format(format, args)

  def append(c: Char): PrintWriter = fileWriter.append(c)

  def printf(l: Locale, format: String, args: AnyRef*): PrintWriter = fileWriter.printf(l, format, args)

  def append(csq: CharSequence): PrintWriter = fileWriter.append(csq)

  def appendWith(csq: CharSequence, start: Int, end: Int): PrintWriter = fileWriter.append(csq, start, end)

  def print[A:Show](x: A): Unit = {
    fileWriter.print(implicitly[Show[A]].stringOf(x))
  }

  def println[A:Show](x: A): Unit = {
    fileWriter.println(implicitly[Show[A]].stringOf(x))
  }

  def eol(): Unit = fileWriter.println()

  def flush(): Unit = fileWriter.flush()
}
