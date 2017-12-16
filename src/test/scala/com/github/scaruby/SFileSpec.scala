package com.github.scaruby

import org.scalatest.{DiagrammedAssertions, FunSpec}

class SFileSpec extends FunSpec with DiagrammedAssertions {
  describe("SFile.read()") {
    it("can read from test1.txt") {
      val test1 = SFile.read("txt/test1.txt")
      assert("Test1" === test1)
    }
  }

  describe("SFile#read()") {
    it("can read from test1.txt") {
      val test1 = SFile("txt/test1.txt").read()
      assert("Test1" === test1)
    }
    it("can read emoji from emoji1.txt") {
      val emoji1 = SFile("txt/emoji1.txt").read()
      assert("⏰⏳" === emoji1)
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
    it("can read from test1lines.txt") {
      val test1Lines = SFile.readLines("txt/test1lines.txt")
      assert(test1Lines === Seq(
        "This", "is", "a", "test"
      ))

    }
  }

}
