package com.github.scaruby

class SBase64Spec extends AbstractSpec {
  describe("SBase64") {
    it("encode64() and decode64()") {
      val source = "Hello, World"
      val encoded = SBase64.encode(source.getBytes("UTF-8"))
      val decoded = new String(SBase64.decode(encoded), "UTF-8")
      assert(source === decoded)
    }
  }
}
