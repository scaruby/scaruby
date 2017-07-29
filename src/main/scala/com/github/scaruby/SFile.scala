package com.github.scaruby

import java.io._
import java.net.{URI, URL}
import java.nio.file.Path

class SFile private (val file: File) {
  def this(path: String) {
    this(new File(path))
  }

  override def equals(obj: scala.Any): Boolean = obj match {
    case that:SFile => file.equals(that.file)
    case _ => false
  }

  override def hashCode(): Int = file.hashCode()

  override def toString: String = file.toString

  def path: String = file.getPath

  def name: String = file.getName

  def executable: Boolean = file.canExecute

  def executable_=(canExecutable: Boolean): Boolean = file.setExecutable(canExecutable)

  def setExecutableByOwnerOnly(executable: Boolean, ownerOnly: Boolean): Boolean = file.setExecutable(executable, ownerOnly)

  def readable: Boolean = file.canRead

  def readable_=(canReadable: Boolean): Boolean = file.setReadable(canReadable)

  def exists: Boolean = file.exists()

  def totalSpace: Long = file.getTotalSpace

  def usableSpace: Long = file.getUsableSpace

  def hidden: Boolean = file.isHidden

  def writable: Boolean = file.canWrite

  def writable_=(canWritable: Boolean): Boolean = file.setWritable(canWritable)

  def setWritableByOwnerOnly(canWritable: Boolean, ownerOnly: Boolean): Boolean = file.setWritable(canWritable, ownerOnly)

  def length: Long = file.length

  def parentFile: SFile = new SFile(file.getParentFile)

  def canonicalFile: SFile = new SFile(file.getCanonicalFile)

  def canonicalPath: String = file.getCanonicalPath

  def absoluteFile: SFile = new SFile(file.getAbsoluteFile)

  def absolutePath: String = file.getAbsolutePath

  def parent: String = file.getParent

  def isFile: Boolean = file.isFile

  def isDirectory: Boolean = file.isDirectory

  def isAbsolute: Boolean = file.isAbsolute

  def freeSpace: Long = file.getFreeSpace

  def list: Seq[String] = file.list()

  def mkdirs(): Boolean = file.mkdirs()

  def compareTo(other: SFile): Int = file.compareTo(other.file)

  def listFiles(filter: (SFile) => Boolean): Seq[SFile] = file.listFiles(new FileFilter {
    override def accept(jFile: File): Boolean = filter(new SFile(jFile))
  }).map{f => new SFile(f)}

  def list(filter: (SFile, String) => Boolean): Seq[String] = file.list(new FilenameFilter {
    override def accept(directory: File, name: String): Boolean = filter(new SFile(directory), name)
  })

  def listFiles: Seq[SFile] = file.listFiles().map(f => new SFile(f))

  def lastModified: Long = file.lastModified()

  def lastModified_=(time: Long): Boolean = file.setLastModified(time)

  def delete(): Boolean = file.delete()

  def renameTo(dest: SFile): Boolean = file.renameTo(dest.file)

  def setReadable(readable: Boolean, ownerOnly: Boolean): Boolean = file.setReadable(readable, ownerOnly)

  def deleteOnExit(): Unit = file.deleteOnExit()

  def newFile(): Boolean = file.createNewFile()

  def setReadOnly(): Boolean = file.setReadOnly()

  def mkdir(): Boolean = file.mkdir()

  def open(): SInputStream = this.openInputStream()

  /**
    * Read all texts from this file
    * @return the content of this file
    */
  def read(): String = for {
    reader <- this.openReader()
  } reader.readAll()

  /**
    * Read lines from this file
    * @return the sequence of lines
    */
  def readLines(): Seq[String] = for {
    reader <- this.openReader()
  } reader.readLines()

  /**
    * Write all texts to this file
    */
  def write(content: String): Unit = for {
    writer <- this.openWriter()
  } writer.print(content)

  /**
    * Opens this `SURL`, calls the `block` with opened `SInputStream`, and closes it.
    * @param block called with `SInputStream`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openInputStreamWith[B](block: SInputStream => B): B = using(new SFileInputStream(this.file))(block)

  /**
    * Opens this `SURL` and returns a `SInputStream` bounded to it.
    * @return `SInputStream` bounded to the URL
    */
  def openInputStream(): SInputStream = new SFileInputStream(this.file)

  /**
    * Opens this `SURL` and returns a `SOutputStream` bounded to it.
    * @return `SOutputStream` bounded to the URL
    */
  def openOutputStream(): SOutputStream = new SFileOutputStream(this.file)

  /**
    * Opens this `SURL`, calls the `block` with opened `SOutputStream`, and closes it.
    * @param block called with `SOutputStream`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openOutputStreamWith[B](block: SOutputStream => B): B = using(new SFileOutputStream(this.file))(block)

  /**
    * Opens this `SURL`, calls the `block` with opened `SReader`, and closes it.
    * @param block called with `SReader`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openReaderWith[B](block: SReader => B): B = {
    using(new SFileReader(this.file))(block)
  }

  /**
    * Opens this `SURL` and returns a `SReader` bounded to it.
    * @return `SReader` bounded to the URL
    */
  def openReader(): SReader = new SFileReader(this.file)

  /**
    * Opens this `SURL`, calls the `block` with opened `SWriter`, and closes it.
    * @param block called with `SWriter`
    * @tparam B return type of the `block`
    * @return the result of the invocation of `block`
    */
  def openWriterWith[B](block: SWriter => B): B = {
    using(new SFileWriter(this.file))(block)
  }

  /**
    * Opens this `SURL` and returns a `SWriter` bounded to it.
    * @return `SWriter` bounded to the URL
    */
  def openWriter(): SWriter = new SFileWriter(this.file)

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

  /**
    * Reads from the file represented by `path`
    * @param path points to a file
    */
  def readBytes(path: String): Array[Byte] = openInputStream(path){ in =>
    in.readAll()
  }

  /**
    * Writes `content` to the file represented by `path`
    * @param path points to a file
    */
  def write(path: String, content: String, encoding: String = DefaultEncoding): Unit = openWriter(path, encoding){writer =>
    writer.writeString(content)
  }

  /**
    * Writes `buffer` to the file represented by `path`
    * @param path points to a file
    * @param buffer a byte buffer to be written
    */
  def write(path: String, buffer: Array[Byte]): Unit = openOutputStream(path){stream =>
    stream.writeAll(buffer)
  }

  /**
    * Writes `lines` to the file represented by `path`
    * @param path points to a file
    * @param lines a sequence of lines
    */
  def writeLines(path: String, lines: Seq[String], encoding: String = DefaultEncoding): Unit = openWriter(path,encoding){writer =>
    for {
      line <- lines
    } writer.println(line)
  }
}

