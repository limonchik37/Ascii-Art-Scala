package images

import pixels.{Pixel, PixelVolumeInterface}

/**
 * Represents a raster-based image where each pixel is stored in a structured format.
 *
 * @param width  The width of the raster image.
 * @param height The height of the raster image.
 * @param pixels The underlying pixel volume interface to manage pixel data.
 *               This allows for reading, writing, and managing pixels efficiently.
 * @tparam T The type of pixel stored in the raster, constrained to extend the Pixel trait.
 */
class RasterImage[T <: Pixel](width: Int, height: Int, pixels: PixelVolumeInterface[T]) {

  /**
   * Retrieves a pixel from the specified (x, y) coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return An `Option` containing the pixel if it exists; otherwise, `None`.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  def getPixel(x: Int, y: Int): Option[T] = {
    coordinatesIsValid(x, y) // Ensure coordinates are valid
    pixels.read(x, y)        // Retrieve the pixel from the pixel volume
  }

  /**
   * Sets a pixel at the specified (x, y) coordinates.
   *
   * @param x     The x-coordinate where the pixel will be set.
   * @param y     The y-coordinate where the pixel will be set.
   * @param pixel The pixel to set at the specified coordinates.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  def setPixel(x: Int, y: Int, pixel: T): Unit = {
    coordinatesIsValid(x, y) // Ensure coordinates are valid
    pixels.create(x, y, pixel) // Add the pixel to the pixel volume
  }

  /**
   * Validates whether the given (x, y) coordinates are within the bounds of the raster image.
   *
   * @param x The x-coordinate to validate.
   * @param y The y-coordinate to validate.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  private def coordinatesIsValid(x: Int, y: Int): Unit = {
    if (x < 0 || y < 0 || x >= width || y >= height) { // Check bounds
      throw new IllegalArgumentException(s"Coordinates ($x, $y) are out of bounds.")
    }
  }
}