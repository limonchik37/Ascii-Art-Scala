package pixels

/**
 * Implementation of a pixel storage system using a mutable map to store pixels at specific coordinates.
 *
 * @tparam T The type of pixel to be stored, which must extend the Pixel trait.
 */
class PixelVolume[T <: Pixel] extends PixelVolumeInterface[T] {

  // Internal storage for pixels, where keys are coordinate pairs (x, y) and values are pixels.
  private val volume: scala.collection.mutable.Map[(Int, Int), T] = scala.collection.mutable.Map.empty

  /**
   * Creates a new pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @param pixel The pixel to be stored.
   * @throws IllegalArgumentException if a pixel already exists at the given coordinates.
   */
  override def create(x: Int, y: Int, pixel: T): Unit = {
    if (read(x, y).isDefined) {
      throw new IllegalArgumentException(s"Pixel already exists at coordinates ($x, $y).")
    }
    volume += ((x, y) -> pixel) // Add the pixel to the map
  }

  /**
   * Reads a pixel from the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return An Option containing the pixel if it exists, or None otherwise.
   */
  override def read(x: Int, y: Int): Option[T] = {
    volume.get((x, y)) // Retrieve the pixel from the map
  }

  /**
   * Updates an existing pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @param pixel The new pixel to replace the existing one.
   * @throws IllegalArgumentException if the pixel data is null or no pixel exists at the given coordinates.
   */
  override def update(x: Int, y: Int, pixel: T): Unit = {
    if (pixel == null) {
      throw new IllegalArgumentException("Pixel data cannot be empty or null.")
    }
    if (read(x, y).isEmpty) {
      throw new IllegalArgumentException(s"No pixel exists at coordinates ($x, $y) to update.")
    }
    volume += ((x, y) -> pixel) // Update the pixel in the map
  }
}