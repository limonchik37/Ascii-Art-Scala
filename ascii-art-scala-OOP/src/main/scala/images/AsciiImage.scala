package images

import pixels.AsciiPixel

/**
 * Concrete implementation of AbstractImage for ASCII-based images.
 *
 * @param width The width of the ASCII image.
 * @param height The height of the ASCII image.
 * @param data The raster data containing ASCII pixels.
 */
class AsciiImage(width: Int, height: Int, data: RasterImage[AsciiPixel]) 
  extends AbstractImage[AsciiPixel](width, height, data) {

  /**
   * Converts the ASCII image to a string representation.
   * @return The string representation of the ASCII image.
   */
  def toAsciiString: String = {
    val builder = new StringBuilder
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        builder.append(getPixel(x, y).map(_.c).getOrElse(' '))
      }
      builder.append("\n")
    }
    builder.toString()
  }
}
