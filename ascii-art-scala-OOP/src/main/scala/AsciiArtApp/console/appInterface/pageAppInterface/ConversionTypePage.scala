package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class ConversionTypePage(command:String)extends ConsoleTextPage{

  override def load(): String = s"Converting image by $command method\n"
}