package com.github.scaruby

import scala.collection.mutable
import scala.collection.immutable

package object collection {
  implicit class RichBuffer[A](val self: mutable.Buffer[A]) extends AnyVal {
    /**
      * This method is like `map`.  But it replace element with
      * `f(element)` and destructive.
      * @param block applied to each element
      * @return self
      */
    def map_!(block: A => A): self.type = {
      var i = 0
      while(i < self.size) {
        self.update(i, block(self(i)))
        i += 1
      }
      self
    }

    /**
      * Remove all elements that satisfy predicate.
      * @param predicate applied to each element
      * @return self
      */
    def deleteIf_!(predicate: A => Boolean): self.type = {
      var i = self.size - 1
      while(i >= 0) {
        if(predicate(self(i))) {
          self.remove(i)
        }
        i -= 1
      }
      self
    }

    /**
      * for each i in range, evaluate self(i) = element
      * @param range
      * @param element
      * @return
      */
    def update(range: Range, element: A): self.type = {
      for(i <- range) {
        self(i) = element
      }
      self
    }

    /**
      * Alias of foreach
      * @param block
      * @tparam B
      */
    def each[B](block: A => B): Unit = {
      self.foreach(block)
    }

    /**
      * Apply block for the index of each element
      * @param block
      */
    def eachIndex(block: Int => Unit): Unit = {
      var i = 0
      while(i < self.size) {
        block(i)
        i += 1
      }
    }
  }
}
