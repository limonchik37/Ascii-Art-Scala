package pixels

/**
 * Interface for managing a collection of pixels at specific coordinates.
 * Defines the basic operations for creating, reading, and updating pixel data.
 *
 * @tparam T The type of pixel to be managed, which must extend the Pixel trait.
 */
trait PixelVolumeInterface[T <: Pixel] {

  /**
   * Adds a new pixel to the specified coordinates.
   *
   * @param x The x-coordinate where the pixel will be added.
   * @param y The y-coordinate where the pixel will be added.
   * @param pixel The pixel to be added.
   * @throws IllegalArgumentException if the coordinates already have an existing pixel.
   */
  def create(x: Int, y: Int, pixel: T): Unit

  /**
   * Retrieves the pixel at the specified coordinates.
   *
   * @param x The x-coordinate of the pixel to retrieve.
   * @param y The y-coordinate of the pixel to retrieve.
   * @return An Option containing the pixel if it exists, or None otherwise.
   */
  def read(x: Int, y: Int): Option[T]

  /**
   * Updates the pixel at the specified coordinates.
   *
   * @param x The x-coordinate where the pixel will be updated.
   * @param y The y-coordinate where the pixel will be updated.
   * @param pixel The new pixel data to update.
   * @throws IllegalArgumentException if no pixel exists at the specified coordinates or the provided pixel is null.
   */
  def update(x: Int, y: Int, pixel: T): Unit
}