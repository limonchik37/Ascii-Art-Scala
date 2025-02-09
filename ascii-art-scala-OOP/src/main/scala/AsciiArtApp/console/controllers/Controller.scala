package AsciiArtApp.console.controllers

/**
 * A trait defining the interface for a controller in the Ascii Art application.
 * The controller manages image loading, processing, and output operations.
 */
trait Controller {

  /**
   * Loads a randomly generated image.
   */
  def loadRandomImage(): Unit

  /**
   * Loads an image from a specified file path.
   *
   * @param path The file path of the image.
   */
  def loadImage(path: String): Unit

  /**
   * Rotates the currently loaded image by the specified degrees.
   *
   * @param degrees The degrees of rotation (e.g., 90, 180, 270).
   */
  def rotateImage(degrees: Int): Unit

  /**
   * Adjusts the brightness of the currently loaded image.
   *
   * @param value The brightness adjustment value.
   */
  def changeBrightness(value: Int): Unit

  /**
   * Converts the currently loaded image to non-linear ASCII art.
   */
  def convertImageToNonLinearAscii(): Unit

  /**
   * Converts the currently loaded image to linear ASCII art.
   */
  def convertImageToLinearAscii(): Unit

  /**
   * Sets a custom palette for linear ASCII conversion.
   *
   * @param palette The ASCII palette string.
   */
  def setLinearAsciiPalette(palette: String): Unit

  /**
   * Inverts the colors of the currently loaded image.
   */
  def InvertImage(): Unit

  /**
   * Outputs the converted ASCII image to the console.
   */
  def outputImageToConsole(): Unit

  /**
   * Saves the converted ASCII image to a specified file path.
   *
   * @param path The file path for saving the ASCII image.
   */
  def saveImageToFile(path: String): Unit

  /**
   * Terminates the application.
   */
  def terminate(): Unit
}