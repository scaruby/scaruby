package com.github.scaruby

import org.scalatest.{DiagrammedAssertions, FunSpec}

class TextFileReadSpec extends FunSpec with DiagrammedAssertions {
  describe("SFile.read()") {
    it("Test1.txt") {
      val test1 = SFile.read("txt/Test1.txt")
      assert("Test1" === test1)
    }
  }

  describe("SFile.readLines()") {
    it("Test1Lines.txt") {
      val test1Lines = SFile.readLines("txt/Test1Lines.txt")
      assert(test1Lines === Seq(
        "This", "is", "a", "test"
      ))

    }
  }
}
