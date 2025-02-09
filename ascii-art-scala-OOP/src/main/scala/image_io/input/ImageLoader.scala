package image_io.input

import images.RGBImage
import pixels.Pixel

/**
 * Trait defining the interface for image loaders.
 * Implementations of this trait are responsible for loading images of specific types.
 *
 * @tparam A The input type (e.g., file path or other representation).
 * @tparam T The type of pixel the image contains.
 */
trait ImageLoader[A, T <: Pixel] {

  /**
   * Loads an image from the given input.
   *
   * @param input The input source for the image (e.g., file path).
   * @return A loaded `RGBImage`.
   */
  def loadImage(input: A): RGBImage
}
