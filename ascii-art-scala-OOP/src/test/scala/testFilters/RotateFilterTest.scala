package testFilters

import filters.implementaions.RotateFilter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{ImagePixel, PixelVolume}

class RotateFilterTest extends AnyFunSuite {

  test("RotateFilter should correctly rotate the image by 90 degrees") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(255, 0, 0),
        (1, 0) -> ImagePixel(0, 255, 0),
        (2, 0) -> ImagePixel(0, 0, 255),
        (0, 1) -> ImagePixel(255, 255, 0),
        (1, 1) -> ImagePixel(255, 0, 255),
        (2, 1) -> ImagePixel(0, 255, 255)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 2, pixelVolume)
    val rgbImage = new RGBImage(3, 2, rasterImage)

    val rotateFilter = new RotateFilter(90)
    val rotatedImage = rotateFilter.apply(rgbImage)

    assert(rotatedImage.getWidth == 2)
    assert(rotatedImage.getHeight == 3)
    assert(rotatedImage.getPixel(0, 0).contains(ImagePixel(255, 255, 0)))
    assert(rotatedImage.getPixel(0, 1).contains(ImagePixel(255, 0, 255)))
    assert(rotatedImage.getPixel(0, 2).contains(ImagePixel(0, 255, 255)))
    assert(rotatedImage.getPixel(1, 0).contains(ImagePixel(255, 0, 0)))
    assert(rotatedImage.getPixel(1, 1).contains(ImagePixel(0, 255, 0)))
    assert(rotatedImage.getPixel(1, 2).contains(ImagePixel(0, 0, 255)))
  }


  test("RotateFilter should correctly rotate the image by 180 degrees") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(255, 0, 0),
        (1, 0) -> ImagePixel(0, 255, 0),
        (2, 0) -> ImagePixel(0, 0, 255),
        (0, 1) -> ImagePixel(255, 255, 0),
        (1, 1) -> ImagePixel(255, 0, 255),
        (2, 1) -> ImagePixel(0, 255, 255)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 2, pixelVolume)
    val rgbImage = new RGBImage(3, 2, rasterImage)

    val rotateFilter = new RotateFilter(180)
    val rotatedImage = rotateFilter.apply(rgbImage)

    assert(rotatedImage.getWidth == 3)
    assert(rotatedImage.getHeight == 2)
    assert(rotatedImage.getPixel(0, 0).contains(ImagePixel(0, 255, 255)))
    assert(rotatedImage.getPixel(1, 0).contains(ImagePixel(255, 0, 255)))
    assert(rotatedImage.getPixel(2, 0).contains(ImagePixel(255, 255, 0)))
    assert(rotatedImage.getPixel(0, 1).contains(ImagePixel(0, 0, 255)))
    assert(rotatedImage.getPixel(1, 1).contains(ImagePixel(0, 255, 0)))
    assert(rotatedImage.getPixel(2, 1).contains(ImagePixel(255, 0, 0)))
  }

  test("RotateFilter should throw an exception for unsupported rotation angles") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map((0, 0) -> ImagePixel(255, 0, 0))

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val rgbImage = new RGBImage(1, 1, rasterImage)

    assertThrows[IllegalArgumentException] {
      new RotateFilter(45).apply(rgbImage)
    }
    assertThrows[IllegalArgumentException] {
      new RotateFilter(-90).apply(rgbImage)
    }
    assertThrows[IllegalArgumentException] {
      new RotateFilter(360).apply(rgbImage)
    }
  }
}