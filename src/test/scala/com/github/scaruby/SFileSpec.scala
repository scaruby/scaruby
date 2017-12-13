package com.github.scaruby

import org.scalatest.{DiagrammedAssertions, FunSpec}

class SFileSpec extends FunSpec with DiagrammedAssertions {
  describe("SFile.read()") {
    it("can read from Test1.txt") {
      val test1 = SFile.read("txt/Test1.txt")
      assert("Test1" === test1)
    }
  }

  describe("SFile#read()") {
    it("can read from Test1.txt") {
      val test1 = SFile("txt/Test1.txt").read()
      assert("Test1" === test1)
    }
  }

  describe("SFile.write(file, content)") {
    it("can write content to file") {
      SFile.withTempFile{f =>
        val content = "Write Test"
        SFile.write(f.path, content)
        val result = SFile.read(f.path)
        assert(content === result)
      }
    }
  }

  describe("SFile#write(content)") {
    it("can write content to a temporary file") {
      SFile.withTempFile{f =>
        val content = "Write Test"
        f.write(content)
        val result = f.read()
        assert(content === result)
      }
    }
  }

  describe("SFile.readLines()") {
    it("can read from Test1Lines.txt") {
      val test1Lines = SFile.readLines("txt/Test1Lines.txt")
      assert(test1Lines === Seq(
        "This", "is", "a", "test"
      ))

    }
  }

}
