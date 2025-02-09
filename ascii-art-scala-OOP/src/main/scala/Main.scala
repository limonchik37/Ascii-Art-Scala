import AsciiArtApp.console.controllers.ConsoleController
import AsciiArtApp.console.handlers.ConsoleHandler
import facade.ImageProcessing
import palettes.CustomAsciiArtSettings

object Main extends App {

  val customSettings =  CustomAsciiArtSettings()

  val imageProcessor: ImageProcessing = new ImageProcessing(customSettings)

  val controller = new ConsoleController(imageProcessor, customSettings)
  private val handler = new ConsoleHandler(controller)
  handler.run()
}