package converters.imageConverters

import images.{AsciiImage, RasterImage}
import pixels.{AsciiPixel, PixelVolume}

/**
 * A concrete implementation of `ImageConverter` that converts a string representation of ASCII art into an `AsciiImage`.
 */
class StringToAsciiImage extends ImageConverter[String, AsciiPixel] {

  /**
   * Converts a string containing ASCII art into an `AsciiImage`.
   *
   * @param input The input string representing ASCII art, with each line separated by `\n`.
   * @return An `AsciiImage` containing the parsed ASCII characters as pixels.
   */
  override def convert(input: String): AsciiImage = {
    val lines = input.split("\n") // Split the input string into lines.
    val height = lines.length
    val width = if (height > 0) lines(0).length else 0 // Determine width from the first line.

    val pixelVolume = new PixelVolume[AsciiPixel]
    val rasterImage = new RasterImage[AsciiPixel](width, height, pixelVolume)

    // Iterate through each character in the input string and convert it to an AsciiPixel.
    for (y <- 0 until height) {
      val line = lines(y)
      for (x <- 0 until width) {
        val char = line(x)
        val pixel = AsciiPixel(char) // Create an AsciiPixel for the character.
        rasterImage.setPixel(x, y, pixel) // Set the pixel in the raster.
      }
    }

    // Return the constructed AsciiImage.
    new AsciiImage(width, height, rasterImage)
  }
}