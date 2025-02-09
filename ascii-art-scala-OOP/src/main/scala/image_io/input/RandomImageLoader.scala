package image_io.input

import images.{RGBImage, RasterImage}
import pixels.{ImagePixel, PixelVolume}

import scala.util.Random

/**
 * RandomImageLoader generates an image with random RGB pixel values.
 *
 * This class implements the `ImageLoader` interface and is used to create an RGBImage
 * of specified dimensions, where each pixel's red, green, and blue values are randomly generated.
 */
class RandomImageLoader extends ImageLoader[(Int, Int), ImagePixel] {

  /**
   * Generates an RGBImage with random pixel values.
   *
   * @param input A tuple `(width, height)` representing the dimensions of the image.
   * @return An `RGBImage` object containing randomly generated RGB pixels.
   */
  override def loadImage(input: (Int, Int)): RGBImage = {
    val (width, height) = input
    val pixelVolume = new PixelVolume[ImagePixel]
    val rasterImage = new RasterImage[ImagePixel](width, height, pixelVolume)
    val random = new Random()

    // Generate random RGB values for each pixel in the image
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val red = random.nextInt(256)   // Random value for red channel (0-255)
        val green = random.nextInt(256) // Random value for green channel (0-255)
        val blue = random.nextInt(256) // Random value for blue channel (0-255)
        val pixel = ImagePixel(red, green, blue)
        rasterImage.setPixel(x, y, pixel)
      }
    }

    // Return the generated RGBImage
    new RGBImage(width, height, rasterImage)
  }
}