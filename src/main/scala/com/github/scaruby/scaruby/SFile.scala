package com.github.scaruby.scaruby

class SFile {

}

object SFile {
  def openInputStream[A](path: String)(block: SInputStream => A): A = using(new SInputStream(path)){in =>
    block(in)
  }

  def openOutputStream[A](path: String)(block: SOutputStream => A): A = using(new SOutputStream(path)){out =>
    block(out)
  }

  def openReader[A](path: String, encoding: String = DefaultEncoding)(block: SReader => A): A = using(new SReader(path, encoding)){reader =>
    block(reader)
  }

  def openWriter[A](path: String, encoding: String = DefaultEncoding)(block: SWriter => A): A = using(new SWriter(path, encoding)){writer =>
    block(writer)
  }

  def readAllStrings(path: String, encoding: String = DefaultEncoding): String = openReader(path, encoding){reader =>
    reader.readAll()
  }

  def readAllBytes(path: String): Array[Byte] = openInputStream(path){in =>
    in.readAll()
  }
}

