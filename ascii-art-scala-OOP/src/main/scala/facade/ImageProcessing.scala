package facade

import converters.AsciiConverters.{LinearAsciiConverter, NonLinearAsciiConverter}
import filters.implementaions.{BrightnessFilter, InvertFilter, RotateFilter}
import image_io.input.ImageLoader
import image_io.output.{ConsoleExecute, FileExecute}
import images.{AsciiImage, RGBImage}
import palettes.CustomAsciiArtSettings
import pixels.ImagePixel

/**
 * Concrete implementation of the `ImageProcessingInterface`.
 * Provides methods for processing images, including transformations, conversions, and saving.
 *
 * @param customAsciiArtSettings Configuration settings for ASCII conversion, such as palettes.
 */
class ImageProcessing(customAsciiArtSettings: CustomAsciiArtSettings) extends ImageProcessingInterface {

  /**
   * Loads an image from the specified path using the given loader.
   *
   * @param path   The file path of the image to load.
   * @param loader The loader responsible for handling the image format.
   * @return A loaded `RGBImage`.
   */
  override def loadImageFromPath(path: String, loader: ImageLoader[String, ImagePixel]): RGBImage = {
    loader.loadImage(path)
  }

  /**
   * Adjusts the brightness of the given image.
   *
   * @param image          The image to modify.
   * @param inputBrightness The value to adjust the brightness by.
   * @return A brightness-adjusted `RGBImage`.
   */
  override def brightness(image: RGBImage, inputBrightness: Int): RGBImage = {
    BrightnessFilter(inputBrightness).apply(image)
  }

  /**
   * Inverts the colors of the given image.
   *
   * @param image The image to invert.
   * @return An inverted `RGBImage`.
   */
  override def invert(image: RGBImage): RGBImage = {
    InvertFilter.apply(image)
  }

  /**
   * Rotates the given image by the specified degrees.
   *
   * @param image       The image to rotate.
   * @param inputRotate The number of degrees to rotate the image.
   * @return A rotated `RGBImage`.
   */
  override def rotate(image: RGBImage, inputRotate: Int): RGBImage = {
    RotateFilter(inputRotate).apply(image)
  }

  /**
   * Converts the given image to an ASCII representation using a linear mapping.
   *
   * @param image The image to convert.
   * @return A string representing the ASCII art.
   */
  override def linearConversion(image: RGBImage): String = {
    val palette = customAsciiArtSettings.asciiPalette
    val converter = new LinearAsciiConverter(palette)
    converter.convert(image)
  }

  /**
   * Converts the given image to an ASCII representation using a non-linear mapping.
   *
   * @param image The image to convert.
   * @return A string representing the ASCII art.
   */
  override def nonlinearConversion(image: RGBImage): String = {
    val converter = new NonLinearAsciiConverter(customAsciiArtSettings.asciiPalette)
    converter.convert(image)
  }

  /**
   * Saves an ASCII image to a file.
   *
   * @param image The ASCII image to save.
   * @param path  The file path where the image should be saved.
   */
  override def saveIntoFile(image: AsciiImage, path: String): Unit = {
    val fileSaver = FileExecute(path)
    fileSaver.outputImage(image)
  }

  /**
   * Outputs an ASCII image to the console.
   *
   * @param image The ASCII image to output.
   */
  override def outputConsole(image: AsciiImage): Unit = {
    val consoleSaver = new ConsoleExecute()
    consoleSaver.outputImage(image)
  }
}