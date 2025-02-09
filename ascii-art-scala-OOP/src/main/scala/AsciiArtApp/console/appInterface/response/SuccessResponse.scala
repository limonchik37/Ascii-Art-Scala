package AsciiArtApp.console.appInterface.response

import AsciiArtApp.console.appInterface.ConsoleTextPage

class SuccessResponse() extends ConsoleTextPage{

  override def load(): String = s"Success!\n"
}