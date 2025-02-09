package images

import pixels.Pixel

/**
 * Abstract class representing a generic image with pixels.
 *
 * @param width The width of the image.
 * @param height The height of the image.
 * @param data The raster data containing the image pixels.
 * @tparam T The type of pixel (e.g., RGBPixel or AsciiPixel).
 */
abstract class AbstractImage[T <: Pixel](val width: Int, val height: Int, val data: RasterImage[T]) {
  require(height > 0 && width > 0, "Width and height must be positive.")

  /**
   * Gets the height of the image.
   * @return The height of the image.
   */
  def getHeight: Int = height

  /**
   * Gets the width of the image.
   * @return The width of the image.
   */
  def getWidth: Int = width

  /**
   * Retrieves a pixel at the specified coordinates.
   * @param x The x-coordinate.
   * @param y The y-coordinate.
   * @return An optional pixel at the given coordinates.
   */
  def getPixel(x: Int, y: Int): Option[T] = data.getPixel(x, y)

  /**
   * Sets a pixel at the specified coordinates.
   * @param x The x-coordinate.
   * @param y The y-coordinate.
   * @param pixel The pixel to set.
   */
  def setPixel(x: Int, y: Int, pixel: T): Unit = data.setPixel(x, y, pixel)
}
