package com.github.scaruby.collection

import org.scalatest.{DiagrammedAssertions, FunSpec}

import scala.collection.mutable

class RichMapSpec extends FunSpec with DiagrammedAssertions {
  describe("RichMap") {
    val source = Map("X" -> 1, "Y" -> 2, "Z" -> 3)
    it("keyOf") {
      assert(Some("X") == source.keyOf(1))
      assert(Some("Y") == source.keyOf(2))
      assert(Some("Z") == source.keyOf(3))
      assert(None == source.keyOf(4))
    }
    it("valuesOf") {
      assert(Seq(1, 2) == source.valuesOf("X", "Y"))
      assert(Seq(1, 3) == source.valuesOf("X", "Z"))
      assert(Seq(3, 1) == source.valuesOf("Z", "X"))
      assert(Seq(1, 2, 3) == source.valuesOf("X", "Y", "Z"))
    }
    it("invert") {
      assert(Map(1 -> "X", 2 -> "Y", 3 -> "Z") == source.invert)
    }
  }
}
