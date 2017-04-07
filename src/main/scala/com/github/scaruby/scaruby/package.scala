package com.github.scaruby
import scala.language.reflectiveCalls

package object scaruby {
  def using[A <: { def close(): Unit }, B](resource: A)(block: A => B): B = {
    try {
      block(resource)
    } finally {
      resource.close()
    }
  }
}
