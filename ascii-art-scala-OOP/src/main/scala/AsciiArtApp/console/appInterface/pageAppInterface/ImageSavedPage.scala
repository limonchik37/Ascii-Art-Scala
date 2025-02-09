package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class ImageSavedPage(path: String) extends ConsoleTextPage {

  override def load(): String = s"Your image saved to $path\n"
}