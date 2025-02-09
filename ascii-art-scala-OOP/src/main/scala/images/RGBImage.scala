package images

import pixels.ImagePixel

/**
 * Concrete implementation of AbstractImage for RGB-based images.
 *
 * @param width The width of the RGB image.
 * @param height The height of the RGB image.
 * @param data The raster data containing RGB pixels.
 */
class RGBImage(width: Int, height: Int, data: RasterImage[ImagePixel])
  extends AbstractImage[ImagePixel](width, height, data) {

  /**
   * Adjusts the brightness of the RGB image by a given value.
   * @param value The brightness adjustment value.
   */
  def adjustBrightness(value: Int): Unit = {
    for (y <- 0 until height; x <- 0 until width) {
      getPixel(x, y).foreach { pixel =>
        pixel.red = clamp(pixel.red + value, 0, 255)
        pixel.green = clamp(pixel.green + value, 0, 255)
        pixel.blue = clamp(pixel.blue + value, 0, 255)
      }
    }
  }

  /**
   * Clamps a value to the specified range.
   * @param value The value to clamp.
   * @param min The minimum value.
   * @param max The maximum value.
   * @return The clamped value.
   */
  private def clamp(value: Int, min: Int, max: Int): Int = Math.max(min, Math.min(max, value))
}