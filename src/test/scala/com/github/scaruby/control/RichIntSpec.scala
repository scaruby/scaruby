package com.github.scaruby.control


import scala.collection.mutable
import org.scalatest.diagrams.Diagrams
import org.scalatest.funspec.AnyFunSpec

class RichIntSpec extends AnyFunSpec with Diagrams {
  describe("upTo()") {
    it("adds [1] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      1.upTo(1){i =>
        buf.append(i)
      }
      assert(List(1) == buf.toList)
    }

    it("adds [1, 2, 3] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      1.upTo(3){i =>
        buf.append(i)
      }
      assert(List(1, 2, 3) == buf.toList)
    }

    it("adds [1, 3, 5] to Buffer specifying by = 2") {
      val buf = mutable.Buffer.empty[Int]
      1.upTo(5, by = 2){i =>
        buf.append(i)
      }
      assert(List(1, 3, 5) == buf.toList)
    }
  }

  describe("downTo()") {
    it("adds [1] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      1.downTo(1){i =>
        buf.append(i)
      }
      assert(List(1) == buf.toList)
    }

    it("adds [3, 2, 1] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      3.downTo(1){i =>
        buf.append(i)
      }
      assert(List(3, 2, 1) == buf.toList)
    }

    it("adds [5, 3, 1] to Buffer specifying by = -2") {
      val buf = mutable.Buffer.empty[Int]
      5.downTo(1, by = 2){i =>
        buf.append(i)
      }
      assert(List(5, 3, 1) == buf.toList)
    }
  }

  describe("times()") {
    it("adds [] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      0.times{i =>
        buf.append(i)
      }
      assert(Nil == buf.toList)
    }
    it("adds [0] to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      1.times{i =>
        buf.append(i)
      }
      assert(List(0) == buf.toList)
    }
    it("adds [0, 3) to Buffer") {
      val buf = mutable.Buffer.empty[Int]
      3.times{i =>
        buf.append(i)
      }
      assert(List(0, 1, 2) == buf.toList)
    }
  }
}
