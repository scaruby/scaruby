package com.github.scaruby

import scala.collection.mutable
import scala.collection.immutable

package object collection {
  implicit class RichBuffer[A](val self: mutable.Buffer[A]) extends AnyVal {
    /**
      * Push an `element` to the top of `self`
      * and return self (`Buffer`)
      * @param element pushed element
      * @return self
      */
    def push(element: A): self.type = {
      self += element
      self
    }

    /**
      * Return the element from the top of `self`.
      * This method doesn't change anything and
      * it is synonym of `last`
      * @return top element
      */
    def top: A = {
      self.last
    }

    /**
      * Pop an element from the top of `self`
      * and return the element
      * @return popped element
      */
    def pop(): A = {
      self.remove(self.length - 1)
    }


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

  implicit class RichSeq[A](val self: scala.collection.Seq[A]) extends AnyVal {
    /**
      * Calculate the average of self.
      * @param ev an instance of type class `Fractional[B]`
      * @return the average of self
      */
    def average(implicit ev: Fractional[A]): A = {
      val length = self.length
      ev.div(self.foldLeft(ev.zero){(acc, e) => ev.plus(acc, e)}, ev.fromInt(length))
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
