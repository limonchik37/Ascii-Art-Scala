package AsciiArtApp.console.controllers

import converters.AsciiConverters.ConversionType
import converters.imageConverters.StringToAsciiImage
import facade.ImageProcessing
import image_io.input.*
import images.{AsciiImage, RGBImage}
import palettes.CustomAsciiArtSettings
import pixels.ImagePixel

/**
 * Implementation of the Controller trait for handling console-based user interactions.
 * This class manages image loading, processing, and ASCII art generation.
 *
 * @param facade         The image processing facade for handling operations.
 * @param customSettings The settings for ASCII art generation.
 */
class ConsoleController(facade: ImageProcessing, customSettings: CustomAsciiArtSettings) extends Controller {

  // The currently loaded RGB image.
  var currentImage: Option[RGBImage] = None

  // The current conversion strategy for ASCII art generation.
  var current_converter: Option[ConversionType] = None

  /**
   * Loads a randomly generated image.
   */
  override def loadRandomImage(): Unit = {
    val loader = new RandomImageLoader()
    val randomImage = loader.loadImage((300, 300))
    currentImage = Some(randomImage)
  }

  /**
   * Determines the appropriate loader strategy based on the file extension.
   *
   * @param filePath The path of the image file.
   * @return An ImageLoader instance for the file format.
   */
  private def determineLoader(filePath: String): ImageLoader[String, ImagePixel] = {
    val extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase
    extension match {
      case "gif" => new GIFImageLoader
      case "jpg" | "jpeg" => new JPGImageLoader
      case "png" => new PNGImageLoader
      case _ => throw new UnsupportedOperationException(s"Unsupported file extension: $extension")
    }
  }

  /**
   * Sets the current ASCII conversion strategy.
   *
   * @param conversionStrategy The desired conversion strategy (linear or non-linear).
   */
  private def convertImageWithStrategy(conversionStrategy: ConversionType): Unit = {
    currentImage match {
      case Some(_) => current_converter = Some(conversionStrategy)
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Converts the currently loaded image to non-linear ASCII art.
   */
  override def convertImageToNonLinearAscii(): Unit = {
    convertImageWithStrategy(ConversionType.NonLinear)
  }

  /**
   * Converts the currently loaded image to linear ASCII art.
   */
  override def convertImageToLinearAscii(): Unit = {
    convertImageWithStrategy(ConversionType.Linear)
  }

  /**
   * Sets a custom palette for linear ASCII conversion.
   *
   * @param palette The ASCII palette string.
   */
  override def setLinearAsciiPalette(palette: String): Unit = {
    customSettings.asciiPalette = palette
  }

  /**
   * Loads an image from a specified file path.
   *
   * @param path The file path of the image.
   */
  override def loadImage(path: String): Unit = {
    val loaderStrategy = determineLoader(path)
    currentImage = Some(facade.loadImageFromPath(path, loaderStrategy))
  }

  /**
   * Rotates the currently loaded image by the specified degrees.
   *
   * @param degrees The degrees of rotation (e.g., 90, 180, 270).
   */
  override def rotateImage(degrees: Int): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.rotate(img, degrees))
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Adjusts the brightness of the currently loaded image.
   *
   * @param value The brightness adjustment value.
   */
  override def changeBrightness(value: Int): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.brightness(img, value))
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Inverts the colors of the currently loaded image.
   */
  override def InvertImage(): Unit = {
    currentImage match {
      case Some(img) =>
        currentImage = Some(facade.invert(img))
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Converts the loaded RGB image to an ASCII string representation.
   *
   * @param image The RGB image to convert.
   * @return A string containing the ASCII representation.
   */
  private def convertImageToAsciiString(image: RGBImage): String = {
    current_converter.getOrElse(ConversionType.Linear) match {
      case ConversionType.Linear => facade.linearConversion(image)
      case ConversionType.NonLinear => facade.nonlinearConversion(image)
    }
  }

  /**
   * Converts the current RGB image to an ASCII image representation.
   *
   * @param imageOption The optional current image.
   * @return The ASCII image representation.
   */
  private def getAsciiConvertedImage(imageOption: Option[RGBImage]): AsciiImage = {
    imageOption match {
      case Some(img) =>
        val asciiString = convertImageToAsciiString(img)
        StringToAsciiImage().convert(asciiString)
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Outputs the converted ASCII image to the console.
   */
  override def outputImageToConsole(): Unit = {
    val asciiImage = getAsciiConvertedImage(currentImage)
    facade.outputConsole(asciiImage)
  }

  /**
   * Saves the converted ASCII image to a specified file path.
   *
   * @param path The file path for saving the ASCII image.
   */
  override def saveImageToFile(path: String): Unit = {
    currentImage match {
      case Some(img) =>
        val asciiImage = StringToAsciiImage().convert(facade.linearConversion(img))
        facade.saveIntoFile(asciiImage, path)
      case None => throw new Exception("Image not loaded!")
    }
  }

  /**
   * Terminates the application.
   */
  override def terminate(): Unit = {
    println("Terminating application.")
    System.exit(0)
  }
}
