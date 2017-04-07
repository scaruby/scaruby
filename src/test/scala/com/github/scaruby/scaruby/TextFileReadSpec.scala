package com.github.scaruby.scaruby

import org.scalatest.{DiagrammedAssertions, FunSpec}

class TextFileReadSpec extends FunSpec with DiagrammedAssertions {
  describe("SFile.readAllStrings") {
    it("Test1.txt") {
      val test1 = SFile.readAllStrings("txt/Test1.txt")
      System.err.println(test1)
      assert("Test1" === test1)
    }
  }
}
