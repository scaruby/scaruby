package com.github.scaruby.collection

import com.github.scaruby.AbstractSpec
import org.scalatest.{DiagrammedAssertions, FunSpec}

import scala.collection.mutable

class RichBufferSpec extends FunSpec with DiagrammedAssertions {
  describe("RichBuffer") {
    val source = mutable.Buffer(1, 2, 3, 4, 5)
    it("map_!") {
      val buf = source.clone()
      buf.map_!{i => i + 1}
      assert(List(2, 3, 4, 5, 6) == buf.toList)
    }
    it("deleteIf_!") {
      val buf = source.clone()
      buf.deleteIf_!{i => i > 2}
      assert(List(1, 2) == buf.toList)
    }
    it("eachIndex") {
      val indices = mutable.Buffer.empty[Int]
      val buf = source.clone()
      buf.eachIndex{i =>
        indices.append(i)
      }
      assert(List(0, 1, 2, 3, 4) == indices.toList)
    }
    it("update(Range, A)") {
      val buf = source.clone()
      buf(0 to 2) = 10
      assert(List(10, 10, 10, 4, 5) == buf.toList)
    }
    it("push(1)") {
      val buf = source.clone()
      buf.push(3)
      assert(List(1, 2, 3, 4, 5, 3) == buf.toList)
    }
    it("top") {
      val buf = source.clone()
      val top = buf.top
      assert(5 == top)
    }
    it("pop()") {
      val buf = source.clone()
      val top = buf.pop()
      val rest = buf
      assert(5 == top)
      assert(List(1, 2, 3, 4) == rest.toList)
    }
  }
}
