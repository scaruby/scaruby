package com.github.scaruby

trait SClosableResource[A <: { def close(): Unit}] {
  def self: A
  def use[B](block: A => B): B = using(self){ resource =>
    block(resource)
  }
  def foreach[B](block: A => B): B = use[B](block)
}
