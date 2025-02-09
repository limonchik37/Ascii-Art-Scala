package testFilters

import filters.implementaions.InvertFilter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.{ImagePixel, PixelVolume}

class InvertFilterTest extends AnyFunSuite {

  test("InvertFilter should correctly invert colors of an RGB image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      private val pixels = Map(
        (0, 0) -> ImagePixel(255, 0, 0),  
        (1, 0) -> ImagePixel(0, 255, 0),  
        (2, 0) -> ImagePixel(0, 0, 255),  
        (0, 1) -> ImagePixel(255, 255, 0),
        (1, 1) -> ImagePixel(0, 255, 255),
        (2, 1) -> ImagePixel(255, 0, 255) 
      )

      override def read(x: Int, y: Int): Option[ImagePixel] = pixels.get((x, y))
      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](3, 2, pixelVolume)
    val rgbImage = new RGBImage(3, 2, rasterImage)

    val invertedImage = InvertFilter.apply(rgbImage)

    assert(invertedImage.getPixel(0, 0).contains(ImagePixel(0, 255, 255)))  
    assert(invertedImage.getPixel(1, 0).contains(ImagePixel(255, 0, 255)))  
    assert(invertedImage.getPixel(2, 0).contains(ImagePixel(255, 255, 0)))  
    assert(invertedImage.getPixel(0, 1).contains(ImagePixel(0, 0, 255)))    
    assert(invertedImage.getPixel(1, 1).contains(ImagePixel(255, 0, 0)))    
    assert(invertedImage.getPixel(2, 1).contains(ImagePixel(0, 255, 0)))    
  }

  test("InvertFilter should handle an empty image") {
    val pixelVolume = new PixelVolume[ImagePixel] {
      override def read(x: Int, y: Int): Option[ImagePixel] = None

      override def create(x: Int, y: Int, pixel: ImagePixel): Unit = {}
    }

    val rasterImage = new RasterImage[ImagePixel](1, 1, pixelVolume)
    val rgbImage = new RGBImage(1, 1, rasterImage)

    val invertedImage = InvertFilter.apply(rgbImage)

    assert(invertedImage.getWidth == 1)
    assert(invertedImage.getHeight == 1)
  }
}