package filters.implementaions

import filters.StructuralFilter
import images.{AbstractImage, RGBImage, RasterImage}
import pixels.{ImagePixel, PixelVolume}

/**
 * Filter for rotating an image by a specified degree.
 *
 * @param degrees The angle of rotation (must be 0, 90, 180, or 270).
 */
class RotateFilter(degrees: Int) extends StructuralFilter[ImagePixel] {

  /**
   * Applies the rotation to the given image.
   *
   * @param image The image to rotate.
   * @return A new image rotated by the specified degree.
   */
  override def apply(image: RGBImage): RGBImage = {
    if (degrees % 90 != 0 || degrees < 0 || degrees >= 360) {
      throw new IllegalArgumentException("Rotation degree must be 0, 90, 180, or 270")
    }
    val rotations = (degrees / 90) % 4
    var rotatedImage = image

    for (_ <- 0 until rotations) {
      rotatedImage = rotate90Degrees(rotatedImage)
    }
    rotatedImage
  }

  /**
   * Rotates the image 90 degrees clockwise.
   *
   * @param image The image to rotate.
   * @return A new image rotated 90 degrees clockwise.
   */
  private def rotate90Degrees(image: RGBImage): RGBImage = {
    val newWidth = image.getHeight
    val newHeight = image.getWidth
    val pixelVolume = new PixelVolume[ImagePixel]
    val rasterImage = new RasterImage[ImagePixel](newWidth, newHeight, pixelVolume)

    for (y <- 0 until image.getHeight; x <- 0 until image.getWidth) {
      image.getPixel(x, y).foreach { pixel =>
        rasterImage.setPixel(image.getHeight - 1 - y, x, pixel)
      }
    }

    new RGBImage(newWidth, newHeight, rasterImage)
  }
}