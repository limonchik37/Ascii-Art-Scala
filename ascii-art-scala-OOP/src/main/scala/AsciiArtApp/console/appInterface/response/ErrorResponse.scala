package AsciiArtApp.console.appInterface.response

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class ErrorResponse(message: String) extends ConsoleTextPage{

  override def load(): String = message + "\n"
}