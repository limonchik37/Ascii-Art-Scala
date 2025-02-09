package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

class StartPage extends ConsoleTextPage{
  override def load(): String = s"Hi! You started ASCII art application upload your image or type --guide for familiarization with program.\n"
}