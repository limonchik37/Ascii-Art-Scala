package converters.AsciiConverters

import images.RGBImage
import pixels.ImagePixel

/**
 * Converts an RGB image into an ASCII representation using a linear conversion method.
 *
 * @param asciiPalette A string representing the ASCII characters used for mapping pixel brightness.
 */
class LinearAsciiConverter(asciiPalette: String) extends AsciiConverter {

  /**
   * Converts an RGBImage to an ASCII string.
   *
   * @param image The RGB image to be converted.
   * @return The ASCII string representation of the image.
   */
  override def convert(image: RGBImage): String = {
    val width = image.getWidth
    val height = image.getHeight
    val paletteLength = asciiPalette.length
    val builder = new StringBuilder

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        image.getPixel(x, y) match {
          case Some(pixel) =>
            var greyscale = ((0.3 * pixel.red) + (0.59 * pixel.green) + (0.11 * pixel.blue)).toInt
            if (greyscale < 0) {
              greyscale = 0
            }
            val index = (greyscale / 255.0 * (paletteLength - 1)).round.toInt
            builder.append(asciiPalette(index))
          case None =>
            builder.append(' ')
        }
      }
      builder.append("\n")
    }

    builder.toString()
  }
}
