package AsciiArtApp.console.appInterface

trait ConsoleTextPage extends Page[String]{

  override def load(): String
}