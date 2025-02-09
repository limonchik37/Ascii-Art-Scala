package converters.imageConverters

import converters.Converter
import images.{AbstractImage, RGBImage}
import pixels.Pixel

/**
 * A trait representing an image converter that transforms input data of type `A` into an `AbstractImage`.
 * This trait is parameterized by:
 * - `A`: The type of the input data (e.g., a file, string, or buffered image).
 * - `T <: Pixel`: The pixel type used in the resulting `AbstractImage`.
 */
trait ImageConverter[A, T <: Pixel] extends Converter[A, AbstractImage[T]] {
}