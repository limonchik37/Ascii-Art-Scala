package palettes

/**
 * Trait that defines basic settings for generating ASCII art.
 * Provides a default palette and methods for palette validation.
 */
trait AsciiArtAppSettings {

  /**
   * Default ASCII palette used for generating ASCII art.
   * Each character represents a level of brightness.
   */
  def asciiPalette: String = "@%#*+=-:. "

  /**
   * Validates the provided ASCII palette to ensure it meets the requirements.
   *
   * @param palette The palette string to validate.
   * @throws IllegalArgumentException if the palette is empty or contains duplicate characters.
   */
  protected def validatePalette(palette: String): Unit = {
    require(palette.nonEmpty, "Palette should not be empty") // Palette must have at least one character
    require(palette.toSeq.distinct.length == palette.length, "Palette should not have duplicate characters") // No duplicates allowed
  }
}