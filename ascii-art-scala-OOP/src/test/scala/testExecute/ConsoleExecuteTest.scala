package testExecute

import image_io.output.ConsoleExecute
import images.{AsciiImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{AsciiPixel, PixelVolume}

import java.io.{ByteArrayOutputStream, PrintStream}

class ConsoleExecuteTest extends AnyFunSuite {

  test("ConsoleExecute should output ASCII image to console") {
    val pixelVolume = new PixelVolume[AsciiPixel] {
      private val pixels = Map(
        (0, 0) -> AsciiPixel('A'),
        (1, 0) -> AsciiPixel('B'),
        (2, 0) -> AsciiPixel('C'),
        (0, 1) -> AsciiPixel('D'),
        (1, 1) -> AsciiPixel('E'),
        (2, 1) -> AsciiPixel('F')
      )

      override def read(x: Int, y: Int): Option[AsciiPixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: AsciiPixel): Unit = {}
    }

    val rasterImage = new RasterImage(3, 2, pixelVolume)
    val asciiImage = new AsciiImage(3, 2, rasterImage)

    val outStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outStream)) {
      val consoleExecute = new ConsoleExecute()
      consoleExecute.outputImage(asciiImage)
    }

    val expectedOutput = "ABC\r\nDEF".stripTrailing()
    val actualOutput = outStream.toString.stripTrailing()

    assert(actualOutput == expectedOutput, s"Expected:\n$expectedOutput\nActual:\n$actualOutput")
  }
}