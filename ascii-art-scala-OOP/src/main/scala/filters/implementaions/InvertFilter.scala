package filters.implementaions

import filters.PixelFilter
import images.RGBImage
import pixels.ImagePixel

/**
 * Filter to invert the colors of an image.
 */
object InvertFilter extends PixelFilter[ImagePixel] {

  /**
   * Applies the color inversion to the image.
   *
   * @param image The image to invert.
   * @return A new image with inverted colors.
   */
  override def apply(image: RGBImage): RGBImage = {
    val width = image.getWidth
    val height = image.getHeight

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        image.getPixel(x, y).foreach { pixel =>
          pixel.red = 255 - pixel.red
          pixel.green = 255 - pixel.green
          pixel.blue = 255 - pixel.blue
        }
      }
    }
    image
  }
}