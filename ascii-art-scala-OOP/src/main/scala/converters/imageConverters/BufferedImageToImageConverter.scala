package converters.imageConverters

import images.{RGBImage, RasterImage}
import pixels.{ImagePixel, PixelVolume}

import java.awt.image.BufferedImage

/**
 * A concrete implementation of `ImageConverter` that converts a `BufferedImage` into an `RGBImage`.
 */
class BufferedImageToImageConverter extends ImageConverter[BufferedImage, ImagePixel] {

  /**
   * Converts a `BufferedImage` into an `RGBImage`.
   *
   * @param bufferedImage The input `BufferedImage` to convert.
   * @return An `RGBImage` containing the pixel data from the input image.
   */
  override def convert(bufferedImage: BufferedImage): RGBImage = {
    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight
    val pixelVolume = new PixelVolume[ImagePixel]
    val rasterImage = new RasterImage[ImagePixel](width, height, pixelVolume)

    // Iterate through each pixel in the BufferedImage and extract its RGB values.
    for (y <- 0 until height; x <- 0 until width) {
      val rgb = bufferedImage.getRGB(x, y)
      val red = (rgb >> 16) & 0xFF
      val green = (rgb >> 8) & 0xFF
      val blue = rgb & 0xFF
      val pixel = ImagePixel(red, green, blue)
      rasterImage.setPixel(x, y, pixel) // Set the pixel in the raster.
    }

    // Return the constructed RGBImage.
    new RGBImage(width, height, rasterImage)
  }
}