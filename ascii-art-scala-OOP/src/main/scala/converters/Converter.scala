package converters

/**
 * A generic trait for implementing conversion logic between two types.
 *
 * @tparam A The input type to be converted.
 * @tparam B The output type resulting from the conversion.
 */
trait Converter[A, B] {

  /**
   * Converts the input of type A to an output of type B.
   *
   * @param input The input value to be converted.
   * @return The converted value of type B.
   */
  def convert(input: A): B
}