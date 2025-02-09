package AsciiArtApp.console.handlers

import AsciiArtApp.console.appInterface.pageAppInterface._
import AsciiArtApp.console.appInterface.response.{ErrorResponse, SuccessResponse}
import AsciiArtApp.console.controllers.Controller

/**
 * Handles user input from the console and delegates tasks to the appropriate controller methods.
 *
 * @param controller The controller instance to manage image operations.
 */
class ConsoleHandler(controller: Controller) extends Handler {

  /**
   * Starts the main loop for handling console commands.
   * The loop continues until the user enters "--exit" or "--stop".
   */
  override def run(): Unit = {
    print(StartPage().load())

    var continueLoop = true
    while (continueLoop) {
      print("(″･ิ_･ิ)っ ")
      val command = scala.io.StdIn.readLine().trim

      command match {
        case "--exit" | "--stop" =>
          println("Exiting the application. Goodbye!")
          continueLoop = false

        case _ =>
          try {
            processCommand(command)
          } catch {
            case e: Exception => println(ErrorResponse(e.getMessage).load())
          }
      }
    }
  }

  /**
   * Processes a user command and delegates it to the appropriate handler function.
   *
   * @param command The command string entered by the user.
   */
  def processCommand(command: String): Unit = {
    val tokens = command.split(" ").toList
    val commandIndices = tokens.zipWithIndex.filter { case (token, _) => token.startsWith("--") }.map(_._2)

    if (commandIndices.isEmpty) {
      println(ErrorResponse(s"Unknown command: $command").load())
      return
    }

    commandIndices.foreach { index =>
      tokens(index) match {
        case "--guide" =>
          println(new GuidePage().load())

        case "--image-random" =>
          handleImageRandom()

        case "--image" =>
          handleImage(tokens, index)

        case "--brightness" =>
          handleBrightness(tokens, index)

        case "--rotate" =>
          handleRotate(tokens, index)

        case "--invert" =>
          handleInvert()

        case "--nonlinear-ascii" =>
          handleNonLinearAscii()

        case "--custom-table" =>
          handleCustomTable(tokens, index)

        case "--linear-ascii" =>
          handleLinearAscii()

        case "--output-console" =>
          handleOutputConsole()

        case "--output-file" =>
          handleOutputFile(tokens, index)

        case _ =>
          println(ErrorResponse(s"Unknown command: ${tokens(index)}").load())
      }
    }
  }

  /**
   * Terminates the application and prints a success message.
   */
  private def handleStop(): Unit = {
    controller.terminate()
    println(SuccessResponse().load())
    System.exit(0)
  }

  /**
   * Loads a randomly generated image using the controller and displays a success message.
   */
  private def handleImageRandom(): Unit = {
    controller.loadRandomImage()
    println(LoadImagePage("randomly generated").load())
  }

  /**
   * Loads an image from the specified file path.
   *
   * @param tokens The list of command tokens.
   * @param index The index of the "--image" command in the tokens list.
   */
  private def handleImage(tokens: List[String], index: Int): Unit = {
    if (index + 1 < tokens.length) {
      controller.loadImage(tokens(index + 1))
      println(LoadImagePage(tokens(index + 1)).load())
    } else {
      println(ErrorResponse("Path for --image is missing").load())
    }
  }

  /**
   * Inverts the colors of the loaded image using the controller.
   */
  private def handleInvert(): Unit = {
    controller.InvertImage()
    println(FilterAppliedPage("invert").load())
  }

  /**
   * Adjusts the brightness of the loaded image.
   *
   * @param tokens The list of command tokens.
   * @param index The index of the "--brightness" command in the tokens list.
   */
  private def handleBrightness(tokens: List[String], index: Int): Unit = {
    if (index + 1 < tokens.length) {
      try {
        val brightness = tokens(index + 1).toInt
        controller.changeBrightness(brightness)
        println(FilterAppliedPage("brightness").load())
      } catch {
        case _: NumberFormatException =>
          println(ErrorResponse(s"Invalid brightness value: ${tokens(index + 1)}").load())
      }
    } else {
      println(ErrorResponse("Brightness value for --brightness is missing").load())
    }
  }

  /**
   * Rotates the loaded image by the specified degrees.
   *
   * @param tokens The list of command tokens.
   * @param index The index of the "--rotate" command in the tokens list.
   */
  private def handleRotate(tokens: List[String], index: Int): Unit = {
    if (index + 1 < tokens.length) {
      val degrees = tokens(index + 1).toInt
      controller.rotateImage(degrees)
      println(FilterAppliedPage("rotation").load())
    } else {
      println(ErrorResponse("Degrees for --rotate is missing").load())
    }
  }

  /**
   * Converts the image to non-linear ASCII art.
   */
  private def handleNonLinearAscii(): Unit = {
    controller.convertImageToNonLinearAscii()
    println(ConversionTypePage("Non linear").load())
  }

  /**
   * Sets a custom ASCII palette for linear conversion.
   *
   * @param tokens The list of command tokens.
   * @param index The index of the "--custom-table" command in the tokens list.
   */
  private def handleCustomTable(tokens: List[String], index: Int): Unit = {
    if (index + 1 < tokens.length) {
      controller.setLinearAsciiPalette(tokens(index + 1))
      println(FilterAppliedPage("custom palette").load())
    } else {
      println(ErrorResponse("Palette for --custom-table is missing").load())
    }
  }

  /**
   * Converts the image to linear ASCII art.
   */
  private def handleLinearAscii(): Unit = {
    controller.convertImageToLinearAscii()
    println(new ConversionTypePage("Linear").load())
  }

  /**
   * Outputs the ASCII representation of the image to the console.
   */
  private def handleOutputConsole(): Unit = {
    controller.outputImageToConsole()
    println(new ConversionTypePage("console output").load())
  }

  /**
   * Saves the ASCII representation of the image to a specified file path.
   *
   * @param tokens The list of command tokens.
   * @param index The index of the "--output-file" command in the tokens list.
   */
  private def handleOutputFile(tokens: List[String], index: Int): Unit = {
    if (index + 1 < tokens.length) {
      controller.saveImageToFile(tokens(index + 1))
      println(ImageSavedPage(tokens(index + 1)).load())
    } else {
      println(ErrorResponse("Path for --output-file is missing").load())
    }
  }
}