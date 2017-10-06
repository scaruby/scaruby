package com.github.scaruby

import java.util.Base64

object SBase64 {
  def encode(source: Array[Byte], withoutPadding: Boolean = false): String = {
    val encoder = if(withoutPadding) {
      Base64.getEncoder
    } else {
      Base64.getEncoder.withoutPadding()
    }
    encoder.encodeToString(source)
  }
  def decode(source: String): Array[Byte] = {
    val decoder = Base64.getDecoder
    decoder.decode(source)
  }
}
