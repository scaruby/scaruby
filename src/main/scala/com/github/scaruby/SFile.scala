package com.github.scaruby

import java.io.File

class SFile private (val file: File) {
  def this(path: String) {
    this(new File(path))
  }
  def path: String = file.getPath
}

object SFile {
  def withTempFile[A](prefix: String = "prefix", suffix: String = "suffix")(block: SFile => A): A = {
    val file: SFile = new SFile(File.createTempFile(prefix, suffix))
    try {
      block(file)
    } finally {
      file.file.delete()
    }
  }
  def apply(path: String): SFile = new SFile(path)

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

  def read(path: String, encoding: String = DefaultEncoding): String = openReader(path, encoding){ reader =>
    reader.readAll()
  }

  def readLines(path: String, encoding: String = DefaultEncoding): Seq[String] = openReader(path, encoding){ reader =>
    reader.readLines()
  }

  def readBytes(path: String): Array[Byte] = openInputStream(path){ in =>
    in.readAll()
  }

  def write(path: String, content: String, encoding: String = DefaultEncoding): Unit = openWriter(path, encoding){writer =>
    writer.writeString(content)
  }
}

