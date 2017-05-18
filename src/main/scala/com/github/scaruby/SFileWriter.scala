package com.github.scaruby

import java.io.{FileOutputStream, OutputStreamWriter, PrintWriter}
import java.util.Locale

import com.github.scaruby.typeclass.Show

class SFileWriter(path: String, encoding: String = DefaultEncoding) extends SWriter[SFileWriter] {
  private[this] val fileWriter: PrintWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), encoding))

  /**
    * @inheritdoc
    */
  override def self: SFileWriter = this

  /**
    * @inheritdoc
    */
  override def hasError(): Boolean = fileWriter.checkError()

  /**
    * @inheritdoc
    */
  override def close(): Unit = fileWriter.close()

  /**
    * @inheritdoc
    */
  override def write(buf: Array[Char])(implicit off: Int = 0, len: Int = buf.length): Unit = fileWriter.write(buf, off, len)

  /**
    * @inheritdoc
    */
  override def writeChar(c: Int): Unit = fileWriter.write(c)

  /**
    * @inheritdoc
    */
  override def writeString(s: String)(implicit off: Int = 0, len: Int = s.length): Unit = fileWriter.write(s, off, len)

  /**
    * @inheritdoc
    */
  override def format(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter = fileWriter.format(l, format, args)

  /**
    * @inheritdoc
    */
  override def printf(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter = fileWriter.printf(l, format, args)

  /**
    * @inheritdoc
    */
  override def append(cseq: CharSequence)(implicit start: Int = 0, end: Int = cseq.length()): PrintWriter = fileWriter.append(cseq)

  /**
    * @inheritdoc
    */
  override def appendChar(c: Char): PrintWriter = fileWriter.append(c)

  /**
    * @inheritdoc
    */
  override def print[A:Show](x: A): Unit = {
    fileWriter.print(implicitly[Show[A]].stringOf(x))
  }

  /**
    * @inheritdoc
    */
  override def println[A:Show](x: A): Unit = {
    fileWriter.println(implicitly[Show[A]].stringOf(x))
  }

  /**
    * @inheritdoc
    */
  override def eol(): Unit = fileWriter.println()

  /**
    * @inheritdoc
    */
  override def flush(): Unit = fileWriter.flush()
}
