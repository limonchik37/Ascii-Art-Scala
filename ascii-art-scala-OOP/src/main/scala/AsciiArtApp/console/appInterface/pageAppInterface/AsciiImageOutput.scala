package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class AsciiImageOutput(imageName: String) extends ConsoleTextPage {

  override def load(): String = s"Here's your $imageName converted to ASCII\n"
}