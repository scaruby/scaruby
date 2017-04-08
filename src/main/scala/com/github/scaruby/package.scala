package com.github

import scala.language.reflectiveCalls

package object scaruby {
  final val DefaultEncoding: String = System.getProperty("file.encoding")
  def using[A <: { def close(): Unit }, B](resource: A)(block: A => B): B = {
    try {
      block(resource)
    } finally {
      resource.close()
    }
  }
}
