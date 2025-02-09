package testConverter.AsciiConverters

import converters.AsciiConverters.LinearAsciiConverter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{ImagePixel, PixelVolume, PixelVolumeInterface}

class LinearAsciiConverterTest extends AnyFunSuite {

  test("LinearAsciiConverter should correctly convert an RGB image to ASCII") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(255, 255, 255),
        (1, 0) -> ImagePixel(128, 128, 128),
        (2, 0) -> ImagePixel(0, 0, 0),
        (0, 1) -> ImagePixel(64, 64, 64),
        (1, 1) -> ImagePixel(192, 192, 192),
        (2, 1) -> ImagePixel(0, 0, 255)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 2, pixelVolume)
    val rgbImage = new RGBImage(3, 2, rasterImage)

    val asciiPalette = "@%#*+=-:. "
    val converter = new LinearAsciiConverter(asciiPalette)

    val asciiResult = converter.convert(rgbImage)

    val expectedAscii = " +@\n#:%".stripTrailing

    assert(asciiResult.stripTrailing() == expectedAscii.stripTrailing(), s"Expected:\n$expectedAscii\nActual:\n$asciiResult")
  }

  test("LinearAsciiConverter should handle empty images correctly") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      override def read(x: Int, y: Int): Option[ImagePixel] = None
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val rgbImage = new RGBImage(1, 1, rasterImage)

    val asciiPalette = "@%#*+=-:. "
    val converter = new LinearAsciiConverter(asciiPalette)
    val asciiResult = converter.convert(rgbImage)

    val expectedOutput = " \n"
    assert(asciiResult == expectedOutput, s"Expected:\n$expectedOutput\nActual:\n$asciiResult")
  }
}