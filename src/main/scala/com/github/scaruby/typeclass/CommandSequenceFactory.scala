package com.github.scaruby.typeclass

abstract class CommandSequenceFactory[T] {
  def newInstanceFrom(source: T): Seq[String]
}
object CommandSequenceFactory {
  implicit object SingleCommandSequenceFactory extends CommandSequenceFactory[String] {
    def newInstanceFrom(source: String): Seq[String] = Seq(source)
  }
  implicit object MultipleCommandSequenceFactory extends CommandSequenceFactory[Seq[String]] {
    def newInstanceFrom(source: Seq[String]): Seq[String] = source
  }
}
