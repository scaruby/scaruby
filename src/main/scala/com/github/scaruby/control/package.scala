package com.github.scaruby

package object control {
  implicit final class RichInt(val self: Int) extends AnyVal {
    /**
      * Repeat from `self` to `to` by `by`.
      * @param to requires `self <= to`
      * @param by requires `by > 0`.  Default value of `by` is `1`.
      * @param block called at each loop
      */
    def upTo(to: Int, by: Int = 1)(block: Int => Unit): Unit = {
      var current = self
      while(current <= to) {
        block(current)
        current += by
      }
    }

    /**
      * Repeat from `self` to `to` by `by`.
      * @param to requires `self >= to`
      * @param by requires `by > 0`.  Default value of `by` is `-1`.
      * @param block called at each loop
      */
    def downTo(to: Int, by: Int = 1)(block: Int => Unit): Unit = {
      var current = self
      while(current >= to) {
        block(current)
        current -= by
      }
    }

    /**
      * Repeat `block` `self` times. For example,
      *
      *     3.times {n =>
      *       println(n)
      *     }
      *
      * prints
      *
      *     0
      *     1
      *     2
      *
      * @param block called at each loop
      */
    def times(block: Int => Unit): Unit = {
      var current = 0
      while(current < self) {
        block(current)
        current += 1
      }
    }
  }
}
