package facade

import image_io.input.ImageLoader
import images.{AsciiImage, RGBImage}
import pixels.ImagePixel

/**
 * Trait defining the interface for image processing operations.
 * This interface supports loading, modifying, converting, and saving images.
 */
trait ImageProcessingInterface {

  /**
   * Loads an image from the specified path using the given loader.
   *
   * @param path   The file path of the image to load.
   * @param loader The loader responsible for handling the image format.
   * @return A loaded `RGBImage`.
   */
  def loadImageFromPath(path: String, loader: ImageLoader[String, ImagePixel]): RGBImage

  /**
   * Rotates the given image by the specified degrees.
   *
   * @param image       The image to rotate.
   * @param inputRotate The number of degrees to rotate the image.
   * @return A rotated `RGBImage`.
   */
  def rotate(image: RGBImage, inputRotate: Int): RGBImage

  /**
   * Inverts the colors of the given image.
   *
   * @param image The image to invert.
   * @return An inverted `RGBImage`.
   */
  def invert(image: RGBImage): RGBImage

  /**
   * Converts the given image to an ASCII representation using a linear mapping.
   *
   * @param image The image to convert.
   * @return A string representing the ASCII art.
   */
  def linearConversion(image: RGBImage): String

  /**
   * Converts the given image to an ASCII representation using a non-linear mapping.
   *
   * @param image The image to convert.
   * @return A string representing the ASCII art.
   */
  def nonlinearConversion(image: RGBImage): String

  /**
   * Saves an ASCII image to a file.
   *
   * @param image The ASCII image to save.
   * @param path  The file path where the image should be saved.
   */
  def saveIntoFile(image: AsciiImage, path: String): Unit

  /**
   * Adjusts the brightness of the given image.
   *
   * @param image          The image to modify.
   * @param inputBrightness The value to adjust the brightness by.
   * @return A brightness-adjusted `RGBImage`.
   */
  def brightness(image: RGBImage, inputBrightness: Int): RGBImage

  /**
   * Outputs an ASCII image to the console.
   *
   * @param image The ASCII image to output.
   */
  def outputConsole(image: AsciiImage): Unit
}