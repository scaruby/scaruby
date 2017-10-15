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

  implicit class RichMap[K, V](val self: scala.collection.Map[K, V]) extends AnyVal {
    /**
      * Return a Seq consist of found values.
        Values ordering follows keys ordering.
      */
    def valuesOf(keys: K*): Seq[V] = {
      var values: List[V] = Nil
      keys.foreach{key =>
        self.get(key).foreach{value =>
          values = value :: values
        }
      }
      values.reverse
    }

    /**
      * Find a key which corresponds value and return the wrapped value or None.
      * If the number of key >= 1, It is unspecified that which key is selected.
      * @param value
      * @return
      */
    def keyOf(value: V): Option[K] = self.find{ case (k, v) => v == value}.map{_._1}

    /**
      * Return the new Map that swap key and value.
      * If the several key is bound to one value, the result is unknown
      * @return
      */
    def invert: scala.collection.Map[V, K] = self.map{ case (k, v) => (v, k) }
  }
}
