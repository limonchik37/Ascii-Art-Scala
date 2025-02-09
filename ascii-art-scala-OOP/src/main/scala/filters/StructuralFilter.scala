package filters

import images.RGBImage
import pixels.Pixel

/**
 * Trait for filters that apply structural transformations to an image,
 * such as resizing, rotation, or flipping.
 *
 * @tparam T The type of pixel the filter operates on.
 */
trait StructuralFilter[T <: Pixel] {

  /**
   * Applies the structural transformation to the given image.
   *
   * @param image The image to transform.
   * @return A new image with the applied structural transformations.
   */
  def apply(image: RGBImage): RGBImage
}