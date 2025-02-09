package testFilters

import filters.implementaions.BrightnessFilter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{ImagePixel, PixelVolume}

class BrightnessFilterTest extends AnyFunSuite {

  test("BrightnessFilter should correctly increase brightness of an RGB image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(100, 100, 100),
        (1, 0) -> ImagePixel(50, 50, 50),
        (2, 0) -> ImagePixel(200, 200, 200)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 1, pixelVolume)
    val rgbImage = new RGBImage(3, 1, rasterImage)

    val brightnessFilter = new BrightnessFilter(50)
    val brightenedImage = brightnessFilter.apply(rgbImage)

    assert(brightenedImage.getPixel(0, 0).contains(ImagePixel(150, 150, 150)))
    assert(brightenedImage.getPixel(1, 0).contains(ImagePixel(100, 100, 100)))
    assert(brightenedImage.getPixel(2, 0).contains(ImagePixel(250, 250, 250)))
  }

  test("BrightnessFilter should correctly decrease brightness of an RGB image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(100, 100, 100),
        (1, 0) -> ImagePixel(50, 50, 50),
        (2, 0) -> ImagePixel(200, 200, 200)
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 1, pixelVolume)
    val rgbImage = new RGBImage(3, 1, rasterImage)

    val brightnessFilter = new BrightnessFilter(-100)
    val darkenedImage = brightnessFilter.apply(rgbImage)

    assert(darkenedImage.getPixel(0, 0).contains(ImagePixel(0, 0, 0)))
    assert(darkenedImage.getPixel(1, 0).contains(ImagePixel(0, 0, 0)))
    assert(darkenedImage.getPixel(2, 0).contains(ImagePixel(100, 100, 100)))

  }
  test("BrightnessFilter should handle an empty image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      override def read(x: Int, y: Int): Option[ImagePixel] = None

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val rgbImage = new RGBImage(1, 1, rasterImage)

    val brightnessFilter = new BrightnessFilter(50)
    val brightenedImage = brightnessFilter.apply(rgbImage)

    assert(brightenedImage.getWidth == 1)
    assert(brightenedImage.getHeight == 1)
  }
}