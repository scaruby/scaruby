package com.github.scaruby

abstract class CommandAdapter[T] {
  def adapt(source: T): Seq[String]
}
object CommandAdapter {
  implicit object PlainCommand extends CommandAdapter[String] {
    def adapt(source: String): Seq[String] = Seq(source)
  }
  implicit object MultiParameterCommand extends CommandAdapter[Seq[String]] {
    def adapt(source: Seq[String]): Seq[String] = source
  }
}
