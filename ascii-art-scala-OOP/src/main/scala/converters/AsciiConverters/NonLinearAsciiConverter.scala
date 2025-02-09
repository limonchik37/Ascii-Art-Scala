package converters.AsciiConverters

import images.{AbstractImage, RGBImage}
import pixels.ImagePixel

/**
 * Converts an RGB image into an ASCII representation using a non-linear gamma correction method.
 *
 * @param asciiPalette A string representing the ASCII characters used for mapping pixel brightness.
 * @param gamma        The gamma value used for non-linear brightness adjustment.
 */
class NonLinearAsciiConverter(asciiPalette: String, gamma: Double = 2.2) extends AsciiConverter {

  /**
   * Converts an RGBImage to an ASCII string with gamma correction.
   *
   * @param image The RGB image to be converted.
   * @return The ASCII string representation of the image.
   */
  override def convert(image: RGBImage): String = {
    val width = image.getWidth
    val height = image.getHeight
    val paletteLength = asciiPalette.length
    val builder = new StringBuilder

    // Iterates over each pixel in the image.
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        image.getPixel(x, y) match {
          case Some(pixel) =>
            // Converts the RGB pixel to normalized greyscale.
            val greyscale = ((0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)) / 255.0
            // Applies gamma correction to the greyscale value.
            val normalized = Math.pow(greyscale, gamma)
            // Maps the gamma-corrected greyscale value to an index in the ASCII palette.
            val index = (normalized * (paletteLength - 1)).round.toInt
            builder.append(asciiPalette(index))
          case None =>
            builder.append(' ') // Adds a space for missing pixels.
        }
      }
      builder.append("\n") // Moves to the next line after finishing a row.
    }

    builder.toString()
  }
}