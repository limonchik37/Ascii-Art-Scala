package image_io.output

import images.AbstractImage
import pixels.AsciiPixel

import java.io.{File, PrintWriter}

/**
 * Writes an ASCII image to a file.
 *
 * @param path The file path where the ASCII image will be saved.
 */
case class FileExecute(path: String) extends ImageExecute {

  /**
   * Writes the provided ASCII image to the specified file.
   *
   * @param asciiArt The ASCII image to be saved.
   *                 Each pixel is represented by a character in the image.
   * @throws IllegalArgumentException if a pixel is missing in the image.
   */
  override def outputImage(asciiArt: AbstractImage[AsciiPixel]): Unit = {
    val writer = new PrintWriter(new File(path)) // Create a file writer
    try {
      for (y <- 0 until asciiArt.getHeight) { // Iterate over image rows
        for (x <- 0 until asciiArt.getWidth) { // Iterate over image columns
          val pixel = asciiArt.getPixel(x, y).getOrElse(
            throw new IllegalArgumentException(s"Pixel $x $y not found")
          )
          writer.print(pixel.c) // Write the ASCII character for the current pixel
        }
        writer.println() // Write a newline after each row
      }
    } finally {
      writer.close() // Ensure the writer is closed to release resources
    }
  }
}