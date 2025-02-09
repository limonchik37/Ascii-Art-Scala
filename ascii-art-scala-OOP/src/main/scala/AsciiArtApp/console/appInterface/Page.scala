package AsciiArtApp.console.appInterface

trait Page[A] {

  def load(): A
}