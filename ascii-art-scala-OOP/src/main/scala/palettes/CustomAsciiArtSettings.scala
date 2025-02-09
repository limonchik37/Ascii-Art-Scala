package palettes

/**
 * Case class for customizable ASCII art settings.
 * Allows users to define and update their custom ASCII palettes.
 *
 * @param _asciiPalette The initial ASCII palette. Defaults to a predefined palette.
 * @throws IllegalArgumentException if the initial palette is invalid.
 */
case class CustomAsciiArtSettings(private var _asciiPalette: String = "@%#*+=-:. ") extends AsciiArtAppSettings {

  // Validate the initial palette during object creation
  validatePalette(_asciiPalette)

  /**
   * Retrieves the current ASCII palette.
   *
   * @return The current ASCII palette string.
   */
  override def asciiPalette: String = _asciiPalette

  /**
   * Updates the ASCII palette with a new value after validating it.
   *
   * @param newPalette The new ASCII palette string.
   * @throws IllegalArgumentException if the new palette is invalid.
   */
  def asciiPalette_=(newPalette: String): Unit = {
    validatePalette(newPalette) // Validate the new palette
    _asciiPalette = newPalette  // Update the palette
  }
}