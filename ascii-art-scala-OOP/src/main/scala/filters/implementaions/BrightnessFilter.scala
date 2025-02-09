package filters.implementaions

import filters.PixelFilter
import images.{AbstractImage, RGBImage}
import pixels.ImagePixel

/**
 * Filter to adjust the brightness of an image.
 *
 * @param input The value by which the brightness should be adjusted. Positive values increase brightness, negative decrease.
 */
class BrightnessFilter(input: Int) extends PixelFilter[ImagePixel] {

  /**
   * Applies the brightness adjustment to the image.
   *
   * @param image The image to adjust the brightness for.
   * @return A new image with adjusted brightness.
   */
  override def apply(image: RGBImage): RGBImage = {
    val width = image.getWidth
    val height = image.getHeight

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        image.getPixel(x, y).foreach { pixel =>
          val greyscale = ((0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)).toInt
          val newGreyscale = clamp(greyscale + input, 0, 255)
          pixel.red = newGreyscale
          pixel.green = newGreyscale
          pixel.blue = newGreyscale
        }
      }
    }
    image
  }

  /**
   * Clamps the given value between a minimum and maximum value.
   *
   * @param value The value to clamp.
   * @param min   The minimum value.
   * @param max   The maximum value.
   * @return The clamped value.
   */
  private def clamp(value: Int, min: Int, max: Int): Int = Math.max(min, Math.min(max, value))
}
