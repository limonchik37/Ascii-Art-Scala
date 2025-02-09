package testExecute

import image_io.output.FileExecute
import images.{AsciiImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{AsciiPixel, PixelVolume}

import java.io.{BufferedReader, File, FileReader}

class FileExecuteTest extends AnyFunSuite {

  test("FileExecute should write ASCII image to file") {
    val pixelVolume = new PixelVolume[AsciiPixel] {
      private val pixels = Map(
        (0, 0) -> AsciiPixel('X'),
        (1, 0) -> AsciiPixel('Y'),
        (2, 0) -> AsciiPixel('Z'),
        (0, 1) -> AsciiPixel('P'),
        (1, 1) -> AsciiPixel('Q'),
        (2, 1) -> AsciiPixel('R')
      )

      override def read(x: Int, y: Int): Option[AsciiPixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: AsciiPixel): Unit = {}
    }

    val rasterImage = new RasterImage(3, 2, pixelVolume)
    val asciiImage = new AsciiImage(3, 2, rasterImage)

    val tempFile = File.createTempFile("ascii_test", ".txt")
    tempFile.deleteOnExit()

    val fileExecute = FileExecute(tempFile.getAbsolutePath)
    fileExecute.outputImage(asciiImage)

    val reader = new BufferedReader(new FileReader(tempFile))
    val fileContent = Iterator.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
    reader.close()

    val expectedOutput = "XYZ\nPQR"

    assert(fileContent == expectedOutput)
  }
}