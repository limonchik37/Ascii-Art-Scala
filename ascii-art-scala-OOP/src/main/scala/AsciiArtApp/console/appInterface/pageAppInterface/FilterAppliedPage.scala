package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

case class FilterAppliedPage(command: String) extends ConsoleTextPage{
  override def load(): String = s"Your $command filter was applied.\n"
}