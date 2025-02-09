package testFacade

import facade.ImageProcessing
import image_io.input.ImageLoader
import images.{AsciiImage, RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import palettes.CustomAsciiArtSettings
import pixels.{AsciiPixel, ImagePixel, PixelVolume}

class ImageProcessingTest extends AnyFunSuite {

  val mockAsciiPalette = "@%#*+=-:. "
  val customAsciiArtSettings = CustomAsciiArtSettings(mockAsciiPalette)
  val imageProcessing = new ImageProcessing(customAsciiArtSettings)

  test("ImageProcessing should load an image using the provided loader") {
    val mockLoader = new ImageLoader[String, ImagePixel] {
      override def loadImage(path: String): RGBImage = {
        val pixelVolume = new PixelVolume[ImagePixel] {
          override def read(x: Int, y: Int): Option[ImagePixel] = Some(ImagePixel(255, 0, 0))

          override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
        }
        val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
        new RGBImage(1, 1, rasterImage)
      }
    }

    val image = imageProcessing.loadImageFromPath("dummy/path", mockLoader)
    assert(image.getWidth == 1)
    assert(image.getHeight == 1)
    assert(image.getPixel(0, 0).contains(ImagePixel(255, 0, 0)))
  }

  test("ImageProcessing should adjust the brightness of an image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map((0, 0) -> ImagePixel(100, 100, 100))

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }
    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val image = new RGBImage(1, 1, rasterImage)

    val brightenedImage = imageProcessing.brightness(image, 50)
    assert(brightenedImage.getPixel(0, 0).contains(ImagePixel(150, 150, 150)))
  }

  test("ImageProcessing should invert the colors of an image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map((0, 0) -> ImagePixel(255, 0, 0))

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }
    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val image = new RGBImage(1, 1, rasterImage)

    val invertedImage = imageProcessing.invert(image)
    assert(invertedImage.getPixel(0, 0).contains(ImagePixel(0, 255, 255)))
  }

  test("ImageProcessing should rotate the image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map((0, 0) -> ImagePixel(255, 0, 0))

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }
    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val image = new RGBImage(1, 1, rasterImage)

    val rotatedImage = imageProcessing.rotate(image, 90)
    assert(rotatedImage.getWidth == 1) // Add appropriate rotation tests if needed
  }

  test("ImageProcessing should convert an image to ASCII using linear conversion") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map((0, 0) -> ImagePixel(255, 255, 255))

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }
    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val image = new RGBImage(1, 1, rasterImage)

    val asciiArt = imageProcessing.linearConversion(image)
    assert(asciiArt == " \n")
  }

  test("ImageProcessing should save ASCII art to a file") {
    val pixelVolume = new PixelVolume[AsciiPixel] {
      override def read(x: Int, y: Int): Option[AsciiPixel] = Some(AsciiPixel('@'))

      override def create(x: Int, y: Int, pixel: AsciiPixel): Unit = {}
    }
    val rasterImage = new RasterImage[AsciiPixel](1, 1, pixelVolume)
    val asciiImage = new AsciiImage(1, 1, rasterImage)

    val filePath = "test_ascii_art.txt"
    imageProcessing.saveIntoFile(asciiImage, filePath)

    val fileContent = scala.io.Source.fromFile(filePath).mkString
    assert(fileContent == "@\r\n")
    new java.io.File(filePath).delete()
  }

  test("ImageProcessing should output ASCII art to console") {
    val pixelVolume = new PixelVolume[AsciiPixel] {
      override def read(x: Int, y: Int): Option[AsciiPixel] = Some(AsciiPixel('@'))

      override def create(x: Int, y: Int, pixel: AsciiPixel): Unit = {}
    }
    val rasterImage = new RasterImage[AsciiPixel](1, 1, pixelVolume)
    val asciiImage = new AsciiImage(1, 1, rasterImage)

    val outStream = new java.io.ByteArrayOutputStream()
    Console.withOut(outStream) {
      imageProcessing.outputConsole(asciiImage)
    }
    assert(outStream.toString == "@\r\n")
  }
}