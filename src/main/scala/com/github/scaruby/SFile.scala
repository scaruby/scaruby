package com.github.scaruby

import java.io.File

class SFile private (val file: File) {
  def this(path: String) {
    this(new File(path))
  }
  def path: String = file.getPath
}

object SFile {
  def withTempFile[A](block: SFile => A): A = {
    val prefix = System.currentTimeMillis().toString
    val file: SFile = new SFile(File.createTempFile(prefix, ""))
    try {
      block(file)
    } finally {
      file.file.delete()
    }
  }
  def apply(path: String): SFile = new SFile(path)

  def openInputStream[A](path: String)(block: SFileInputStream => A): A = using(new SFileInputStream(path)){ in =>
    block(in)
  }

  def openOutputStream[A](path: String)(block: SFileOutputStream => A): A = using(new SFileOutputStream(path)){ out =>
    block(out)
  }

  def openReader[A](path: String, encoding: String = DefaultEncoding)(block: SFileReader => A): A = using(new SFileReader(path, encoding)){ reader =>
    block(reader)
  }

  def openWriter[A](path: String, encoding: String = DefaultEncoding)(block: SFileWriter => A): A = using(new SFileWriter(path, encoding)){ writer =>
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

