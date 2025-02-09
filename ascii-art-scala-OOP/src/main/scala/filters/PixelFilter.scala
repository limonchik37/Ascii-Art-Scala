package filters

import images.RGBImage
import pixels.Pixel

/**
 * Trait for filters that apply pixel-level transformations to an image.
 *
 * @tparam T The type of pixel the filter operates on.
 */
trait PixelFilter[T <: Pixel] {

  /**
   * Applies the pixel-level transformation to the given image.
   *
   * @param image The image to transform.
   * @return A new image with the applied pixel-level transformations.
   */
  def apply(image: RGBImage): RGBImage
}