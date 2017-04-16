package com.github.scaruby

import java.io.{FileOutputStream, OutputStreamWriter, PrintWriter}
import java.util.Locale

class SFileWriter(path: String, encoding: String = DefaultEncoding) extends SClosableResource[SFileWriter] {
  private[this] val fileWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), encoding))

  override def self: SFileWriter = this

  def hasError(): Boolean = fileWriter.checkError()

  def close(): Unit = fileWriter.close()

  def write(buf: Array[Char])(implicit off: Int = 0, len: Int = buf.length): Unit = fileWriter.write(buf, off, len)

  def writeChar(c: Int): Unit = fileWriter.write(c)

  def writeString(s: String)(implicit off: Int = 0, len: Int = s.length): Unit = fileWriter.write(s, off, len)

  def format(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter = fileWriter.format(l, format, args)

  def printf(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter = fileWriter.printf(l, format, args)

  def append(cseq: CharSequence)(implicit start: Int = 0, end: Int = cseq.length()): PrintWriter = fileWriter.append(cseq)

  def appendChar(c: Char): PrintWriter = fileWriter.append(c)

  def print[A:Show](x: A): Unit = {
    fileWriter.print(implicitly[Show[A]].stringOf(x))
  }

  def println[A:Show](x: A): Unit = {
    fileWriter.println(implicitly[Show[A]].stringOf(x))
  }

  def eol(): Unit = fileWriter.println()

  def flush(): Unit = fileWriter.flush()
}
