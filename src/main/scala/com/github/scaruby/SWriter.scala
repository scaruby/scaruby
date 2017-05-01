package com.github.scaruby

import java.io.PrintWriter
import java.util.Locale

/**
  * Created by Mizushima on 2017/05/01.
  */
abstract class SWriter[A <: { def close(): Unit}] extends SClosableResource[A] {

  def hasError(): Boolean

  def write(buf: Array[Char])(implicit off: Int = 0, len: Int = buf.length): Unit

  def writeChar(c: Int): Unit

  def writeString(s: String)(implicit off: Int = 0, len: Int = s.length): Unit

  def format(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter

  def printf(format: String, args: AnyRef*)(implicit l: Locale = DefaultLocale): PrintWriter

  def append(cseq: CharSequence)(implicit start: Int = 0, end: Int = cseq.length()): PrintWriter

  def appendChar(c: Char): PrintWriter

  def print[A:Show](x: A): Unit

  def println[A:Show](x: A): Unit

  def eol(): Unit

  def flush(): Unit

  def close(): Unit
}
