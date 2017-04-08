package com.github.scaruby

/**
  * A type class which translate a value of type A int a String
  * @tparam A
  */
abstract class Show[A] {
  /**
    * Translate this into a String
    * @param param a source value
    * @return a String
    */
  def stringOf(param: A): String
}

object Show {
  implicit object ShowAny extends Show[Any] {
    override def stringOf(param: Any): String = param.toString
  }
  implicit object ShowCharArray extends Show[Array[Char]] {
    override def stringOf(param: Array[Char]): String = param.mkString
  }
  implicit object ShowString extends Show[String] {
    override def stringOf(param: String): String = param
  }
  implicit object ShowByte extends Show[Byte] {
    override def stringOf(param: Byte): String = param.toString
  }
  implicit object ShowShort extends Show[Short] {
    override def stringOf(param: Short): String = param.toString
  }
  implicit object ShowInt extends Show[Int] {
    override def stringOf(param: Int): String = param.toString
  }
  implicit object ShowChar extends Show[Char] {
    override def stringOf(param: Char): String = param.toString
  }
  implicit object ShowLong extends Show[Long] {
    override def stringOf(param: Long): String = param.toString
  }
  implicit object ShowFloat extends Show[Float] {
    override def stringOf(param: Float): String = param.toString
  }
  implicit object ShowDouble extends Show[Double] {
    override def stringOf(param: Double): String = param.toString
  }
  implicit object ShowBoolean extends Show[Boolean] {
    override def stringOf(param: Boolean): String = param.toString
  }
  implicit object ShowUnit extends Show[Unit] {
    override def stringOf(param: Unit): String = "()"
  }
}
