package converters.AsciiConverters

/**
 * A sealed trait representing the type of ASCII conversion to be applied.
 *
 * The sealed modifier ensures that all possible implementations of `ConversionType`
 * are defined in this file, enabling exhaustive pattern matching.
 */
sealed trait ConversionType

/**
 * Companion object for the `ConversionType` trait containing the specific conversion types.
 */
object ConversionType {

  /**
   * Represents a linear ASCII conversion type, where pixel brightness is mapped directly
   * to the ASCII character palette.
   */
  case object Linear extends ConversionType

  /**
   * Represents a non-linear ASCII conversion type, where gamma correction is applied
   * to pixel brightness before mapping to the ASCII character palette.
   */
  case object NonLinear extends ConversionType
}