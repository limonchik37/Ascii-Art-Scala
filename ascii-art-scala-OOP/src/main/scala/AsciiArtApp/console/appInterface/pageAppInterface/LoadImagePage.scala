package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class LoadImagePage(path: String) extends ConsoleTextPage{

  override def load(): String = s"Your Image was loaded from $path\n"
}