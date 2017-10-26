package com.github.scaruby.collection

import org.scalatest.{DiagrammedAssertions, FunSpec}

class RichSeqSpec extends FunSpec with DiagrammedAssertions{
  describe("RichSeq") {
    it("average") {
      val src1 = Seq(1.0, 2.0, 3.0)
      assert(2.0 == src1.average)
      val src2 = Seq(1.0, 2.0, 3.0, 4.0)
      assert(2.5 == src2.average)
    }
    it("&") {
      val src1 = Seq(1, 2, 3)
      val src2 = Seq(2, 3, 4)
      val result = src1 & src2
      assert(Seq(2, 3) == result)
    }
    it("| for Seq") {
      val src1 = Seq(1, 2, 3)
      val src2 = Seq(2, 3, 4)
      val result = src1 | src2
      assert(Seq(1, 2, 3, 2, 3, 4) == result)
    }
    it("| for Set") {
      val src1 = Set(1, 2, 3)
      val src2 = Set(2, 3, 4)
      val result = src1 | src2
      assert(Set(1, 2, 3, 4) == result)
    }
  }
}
