package image_io.input

import converters.imageConverters.BufferedImageToImageConverter
import images.RGBImage
import pixels.ImagePixel

import java.io.File
import javax.imageio.ImageIO

/**
 * Abstract base class for loading images from file paths.
 * Implements common logic for file validation and format checking.
 */
abstract class AbstractImageLoader extends ImageLoader[String, ImagePixel] {

  /**
   * Loads an image from a given file path.
   *
   * @param path The file path of the image to load.
   * @return A loaded `RGBImage`.
   * @throws IllegalArgumentException      If the file path is invalid or the file does not exist.
   * @throws UnsupportedOperationException If the file format is not supported.
   */
  override def loadImage(path: String): RGBImage = {
    val file = new File(path)

    if (!file.exists() || !file.isFile) {
      throw new IllegalArgumentException(s"Invalid file path: $path")
    }

    val extension = getFileExtension(file)
    if (!isSupportedExtension(extension)) {
      throw new UnsupportedOperationException(s"File format $extension is not supported by ${this.getClass.getSimpleName}")
    }

    BufferedImageToImageConverter().convert(ImageIO.read(file))
  }

  /**
   * Checks if a given file extension is supported by the loader.
   *
   * @param extension The file extension to check (e.g., "png").
   * @return `true` if the extension is supported, `false` otherwise.
   */
  protected def isSupportedExtension(extension: String): Boolean

  /**
   * Extracts the file extension from the given file.
   *
   * @param file The file to extract the extension from.
   * @return The file extension as a lowercase string.
   */
  private def getFileExtension(file: File): String = {
    val name = file.getName
    val lastIndex = name.lastIndexOf(".")
    if (lastIndex > 0) name.substring(lastIndex + 1).toLowerCase else ""
  }
}