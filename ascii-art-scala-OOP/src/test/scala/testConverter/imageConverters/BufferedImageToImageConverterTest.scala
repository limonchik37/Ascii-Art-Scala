package testConverter.imageConverters

import converters.imageConverters.BufferedImageToImageConverter
import images.{RGBImage, RasterImage}
import org.scalatest.funsuite.AnyFunSuite
import pixels.ImagePixel

import java.awt.image.BufferedImage

class BufferedImageToImageConverterTest extends AnyFunSuite {

  test("BufferedImageToImageConverter should correctly convert a BufferedImage to an RGBImage") {
    val width = 3
    val height = 2
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    bufferedImage.setRGB(0, 0, (255 << 16) | (0 << 8) | 0)
    bufferedImage.setRGB(1, 0, (0 << 16) | (255 << 8) | 0)
    bufferedImage.setRGB(2, 0, (0 << 16) | (0 << 8) | 255)
    bufferedImage.setRGB(0, 1, (255 << 16) | (255 << 8) | 0)
    bufferedImage.setRGB(1, 1, (255 << 16) | (0 << 8) | 255)
    bufferedImage.setRGB(2, 1, (0 << 16) | (255 << 8) | 255)

    val converter = new BufferedImageToImageConverter()
    val rgbImage = converter.convert(bufferedImage)

    assert(rgbImage.getWidth == width)
    assert(rgbImage.getHeight == height)
    assert(rgbImage.getPixel(0, 0).contains(ImagePixel(255, 0, 0)))
    assert(rgbImage.getPixel(1, 0).contains(ImagePixel(0, 255, 0)))
    assert(rgbImage.getPixel(2, 0).contains(ImagePixel(0, 0, 255)))
    assert(rgbImage.getPixel(0, 1).contains(ImagePixel(255, 255, 0)))
    assert(rgbImage.getPixel(1, 1).contains(ImagePixel(255, 0, 255)))
    assert(rgbImage.getPixel(2, 1).contains(ImagePixel(0, 255, 255)))
  }

  test("BufferedImageToImageConverter should handle empty BufferedImage") {
    val width = 1
    val height = 1
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    bufferedImage.setRGB(0, 0, 0)

    val converter = new BufferedImageToImageConverter()
    val rgbImage = converter.convert(bufferedImage)

    assert(rgbImage.getWidth == width)
    assert(rgbImage.getHeight == height)
    assert(rgbImage.getPixel(0, 0).contains(ImagePixel(0, 0, 0)))
  }
}