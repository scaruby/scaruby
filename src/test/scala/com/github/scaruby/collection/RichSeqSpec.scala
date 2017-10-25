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
  }
}
