package com.github.scaruby.scaruby

trait SClosableResource[A <: { def close(): Unit}] {
  def self: A
  def closeWith[B](block: A => B): B = using(self){resource =>
    block(resource)
  }
}
