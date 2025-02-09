package testConverter.AsciiConverters

import converters.AsciiConverters.NonLinearAsciiConverter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{ImagePixel, PixelVolume, PixelVolumeInterface}

class NonLinearAsciiConverterTest extends AnyFunSuite {

  test("NonLinearAsciiConverter should correctly convert an RGB image to ASCII with default gamma") {
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
    val converter = new NonLinearAsciiConverter(asciiPalette)

    val asciiResult = converter.convert(rgbImage)

    val expectedAscii =" #@\n@=@".stripTrailing

    assert(asciiResult.stripTrailing() == expectedAscii.stripTrailing(), s"Expected:\n$expectedAscii\nActual:\n$asciiResult")
  }

  test("NonLinearAsciiConverter should correctly apply custom gamma") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(255, 0, 0),
        (1, 0) -> ImagePixel(0, 255, 0),
        (2, 0) -> ImagePixel(0, 0, 255),
        (0, 1) -> ImagePixel(128, 128, 128),
        (1, 1) -> ImagePixel(0, 0, 0),
        (2, 1) -> ImagePixel(255, 255, 255)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 2, pixelVolume)
    val rgbImage = new RGBImage(3, 2, rasterImage)

    val asciiPalette = "@%#*+=-:. "
    val customGamma = 1.8
    val converter = new NonLinearAsciiConverter(asciiPalette, customGamma)

    val asciiResult = converter.convert(rgbImage)

    val expectedAscii = "%*@\n*@ ".stripTrailing()

    assert(asciiResult.stripTrailing() == expectedAscii.stripTrailing(), s"Expected:\n$expectedAscii\nActual:\n$asciiResult")
  }
}