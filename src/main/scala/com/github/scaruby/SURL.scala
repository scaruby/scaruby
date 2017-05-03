package com.github.scaruby

import java.net.URL

class SURL private (val jURL: URL) {
}

object SURL {
  def apply(url: String): SURL = new SURL(new URL(url))
}
