package image_io.output

import images.AbstractImage
import pixels.AsciiPixel

/**
 * Defines a common interface for executing image output operations.
 *
 * Implementations of this trait can define how ASCII images are displayed or saved.
 */
trait ImageExecute {

  /**
   * Outputs the given ASCII image.
   *
   * @param asciiArt The ASCII image to be processed.
   */
  def outputImage(asciiArt: AbstractImage[AsciiPixel]): Unit
}