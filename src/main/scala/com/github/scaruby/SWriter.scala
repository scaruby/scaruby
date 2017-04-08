package com.github.scaruby

import java.io.{FileOutputStream, OutputStreamWriter, PrintWriter}
import java.util.Locale

class SWriter(path: String, encoding: String = DefaultEncoding) extends SClosableResource[SWriter] {
  private[this] val fileWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), encoding))

  override def self: SWriter = this

  def print(obj: scala.Any): Unit = fileWriter.print(obj)

  def close(): Unit = fileWriter.close()

  def write(buf: Array[Char], off: Int, len: Int): Unit = fileWriter.write(buf, off, len)

  def format(l: Locale, format: String, args: AnyRef*): PrintWriter = fileWriter.format(l, format, args)

  def printf(format: String, args: AnyRef*): PrintWriter = fileWriter.printf(format, args)

  def print(d: Double): Unit = fileWriter.print(d)

  def print(c: Char): Unit = fileWriter.print(c)

  def println(x: Int): Unit = fileWriter.println(x)

  def format(format: String, args: AnyRef*): PrintWriter = fileWriter.format(format, args)

  def println(x: Double): Unit = fileWriter.println(x)

  def println(x: scala.Any): Unit = fileWriter.println(x)

  def println(x: Boolean): Unit = fileWriter.println(x)

  def append(c: Char): PrintWriter = fileWriter.append(c)

  def println(x: Long): Unit = fileWriter.println(x)

  def print(s: String): Unit = fileWriter.print(s)

  def write(buf: Array[Char]): Unit = fileWriter.write(buf)

  def printf(l: Locale, format: String, args: AnyRef*): PrintWriter = fileWriter.printf(l, format, args)

  def write(c: Int): Unit = fileWriter.write(c)

  def print(f: Float): Unit = fileWriter.print(f)

  def checkError(): Boolean = fileWriter.checkError()

  def print(s: Array[Char]): Unit = fileWriter.print(s)

  def write(s: String, off: Int, len: Int): Unit = fileWriter.write(s, off, len)

  def print(b: Boolean): Unit = fileWriter.print(b)

  def println(x: Array[Char]): Unit = fileWriter.println(x)

  def append(csq: CharSequence): PrintWriter = fileWriter.append(csq)

  def append(csq: CharSequence, start: Int, end: Int): PrintWriter = fileWriter.append(csq, start, end)

  def println(x: String): Unit = fileWriter.println(x)

  def print(i: Int): Unit = fileWriter.print(i)

  def println(x: Char): Unit = fileWriter.println(x)

  def println(x: Float): Unit = fileWriter.println(x)

  def flush(): Unit = fileWriter.flush()

  def print(l: Long): Unit = fileWriter.print(l)

  def println(): Unit = fileWriter.println()

  def write(s: String): Unit = fileWriter.write(s)
}
