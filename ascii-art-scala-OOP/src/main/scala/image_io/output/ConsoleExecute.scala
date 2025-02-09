package image_io.output

import images.AbstractImage
import pixels.AsciiPixel

/**
 * Outputs an ASCII image to the console.
 */
class ConsoleExecute extends ImageExecute {

  /**
   * Prints the provided ASCII image to the console.
   *
   * @param asciiArt The ASCII image to be printed.
   *                 Each pixel is represented by a character in the image.
   */
  override def outputImage(asciiArt: AbstractImage[AsciiPixel]): Unit = {
    for (y <- 0 until asciiArt.getHeight) {
      for (x <- 0 until asciiArt.getWidth) {
        val pixel = asciiArt.getPixel(x, y).getOrElse(
          throw new IllegalArgumentException("Pixel not found")
        )
        print(pixel.c)
      }
      println()
    }
  }
}