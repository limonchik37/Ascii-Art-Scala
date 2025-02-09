package converters.AsciiConverters

import converters.Converter
import images.RGBImage

/**
 * A specific trait for converting an RGB image to an ASCII string representation.
 * Extends the generic Converter trait.
 */
trait AsciiConverter extends Converter[RGBImage, String] {

}