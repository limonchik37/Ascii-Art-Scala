package testAppInterface

import AsciiArtApp.console.appInterface.pageAppInterface.*
import AsciiArtApp.console.appInterface.response.{ErrorResponse, SuccessResponse}
import AsciiArtApp.console.controllers.Controller
import AsciiArtApp.console.handlers.ConsoleHandler
import org.scalatest.funsuite.AnyFunSuite

import java.io.ByteArrayOutputStream

class ConsoleHandlerTest extends AnyFunSuite {

  private def captureOutput(handler: ConsoleHandler, command: String): String = {
    var outputCapture = new ByteArrayOutputStream()
    Console.withOut(outputCapture) {
      handler.processCommand(command)
    }
    outputCapture.toString.trim
  }

  val fakeController = new Controller {
    override def loadRandomImage(): Unit = {}
    override def loadImage(path: String): Unit = {}
    override def changeBrightness(value: Int): Unit = {}
    override def rotateImage(degrees: Int): Unit = {}
    override def InvertImage(): Unit = {}
    override def convertImageToNonLinearAscii(): Unit = {}
    override def setLinearAsciiPalette(palette: String): Unit = {}
    override def convertImageToLinearAscii(): Unit = {}
    override def outputImageToConsole(): Unit = {}
    override def saveImageToFile(path: String): Unit = {}
    override def terminate(): Unit = {}
  }

  val handler = new ConsoleHandler(fakeController)

  test("--image without path should display error") {
    var output = captureOutput(handler, "--image")
    output = output + "\n"
    assert(output == ErrorResponse(s"Path for --image is missing" ).load())
  }

  test("--image with path should load image") {
    var output = captureOutput(handler, "--image test.jpg")
    output = output + "\n"
    assert(output == LoadImagePage("test.jpg").load())
  }

  test("--rotate without degrees should display error\r") {
    var output = captureOutput(handler, "--rotate")
    output = output + "\n"
    assert(output == ErrorResponse("Degrees for --rotate is missing").load())
  }

  test("--rotate with degrees should apply rotation\r") {
    var output = captureOutput(handler, "--rotate 90")
    output = output + "\n"
    assert(output == FilterAppliedPage("rotation").load())
  }

  test("--brightness without value should display error") {
    var output = captureOutput(handler, "--brightness")
    output = output + "\n"
    assert(output == ErrorResponse("Brightness value for --brightness is missing").load())
  }

  test("--brightness with value should apply brightness") {
    var output = captureOutput(handler, "--brightness 50")
    output = output + "\n"
    assert(output == FilterAppliedPage("brightness").load())
  }

  test("--invert should apply invert filter") {
    var output = captureOutput(handler, "--invert")
    output = output + "\n"
    assert(output == FilterAppliedPage("invert").load())
  }

  test("--linear-ascii should convert image to linear ASCII") {
    var output = captureOutput(handler, "--linear-ascii")
    output = output + "\n"
    assert(output == ConversionTypePage("Linear").load())
  }

  test("--nonlinear-ascii should convert image to non-linear ASCII") {
    var output = captureOutput(handler, "--nonlinear-ascii")
    output = output + "\n"
    assert(output == ConversionTypePage("Non-linear").load())
  }

  test("--output-console should display image in console") {
    var output = captureOutput(handler, "--output-console")
    output = output + "\n"
    assert(output == ConversionTypePage("console output").load())
  }

  test("--output-file without path should display error") {
    var output = captureOutput(handler, "--output-file")
    output = output + "\n"
    assert(output == ErrorResponse("Path for --output-file is missing").load())
  }

  test("--output-file with path should save file") {
    var output = captureOutput(handler, "--output-file output.txt")
    output = output + "\n"
    assert(output == ImageSavedPage("output.txt").load())
  }

  test("--guide should display guide page") {
    var output = captureOutput(handler, "--guide")
    output = output + "\n"
    assert(output == GuidePage().load())
  }

  test("unknown command should display error") {
    var output = captureOutput(handler, "--unknown")
    output = output + "\n"
    assert(output == ErrorResponse("Unknown command: --unknown").load())
  }
}