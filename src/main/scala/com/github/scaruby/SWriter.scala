package com.github.scaruby

import java.io.PrintWriter
import java.util.Locale

import com.github.scaruby.typeclass.Show

/**
  * An object to write characters to a stream.
  */
abstract class SWriter extends SClosableResource[SWriter] {

  /**
    * Returns whether this has error or not
    */
  def hasError(): Boolean

  /**
    * Writes `buffer` to this writer using `offset` and `length`
    * @param buffer to be written
    * @param offset offset of buffer, which can be omitted.
    * @param length length to be written, which is less than buffer.length, which can be omitted.
    */
  def write(buffer: Array[Char])(implicit offset: Int = 0, length: Int = buffer.length): Unit

  /**
    * Writes character as Char to this writer
    */
  def writeChar(character: Int): Unit

  /**
    * Writes `string` to this writer using `offset` adn `length`
    * @param string to be written
    * @param offset offset of buffer, which can be omitted.
    * @param length length to be written, which is less than buffer.length, which can be omitted.
    */
  def writeString(string: String)(implicit offset: Int = 0, length: Int = string.length): Unit

  /**
    * Writes a formatted string to this writer using specified format string and arguments.
    * @param format format string
    * @param args used by format
    * @param locale locale
    * @return this
    */
  def format(format: String, args: AnyRef*)(implicit locale: Locale = DefaultLocale): this.type

  /**
    * Writes a formatted string to this writer using specified format string and arguments.
    * @param format format string
    * @param args used by format
    * @param locale locale
    * @return this
    */
  def printf(format: String, args: AnyRef*)(implicit locale: Locale = DefaultLocale): this.type

  /**
    * Writes a `sequence` to this writer
    * @param sequence
    * @param start start index of sequence to be written
    * @param end end index of sequence to be written
    * @return this
    */
  def append(sequence: CharSequence)(implicit start: Int = 0, end: Int = sequence.length): this.type

  /**
    * Writes a `character` to this writer
    * @param character to be written
    * @return this
    */
  def appendCharacter(character: Char): this.type

  /**
    * Converts a `value` to a String using `Show` type classes and print the String.
    * @param value to be converted
    * @tparam A type parameter of the value
    */
  def print[A:Show](value: A): Unit

  /**
    * Converts a `value` to a String using `Show` type classes, prints the String, and prints EOL.
    * @param value to be converted
    * @tparam A type parameter of the value
    */
  def println[A:Show](value: A): Unit

  /**
    * Prints EOL.
    */
  def printEOL(): Unit

  /**
    * Flushes this writer
    */
  def flush(): Unit

  /**
    * Closes this writer.  After closing,  any method must not be called.
    */
  def close(): Unit
}
