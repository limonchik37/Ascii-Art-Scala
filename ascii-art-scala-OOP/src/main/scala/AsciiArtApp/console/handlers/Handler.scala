package AsciiArtApp.console.handlers

/**
 * A trait representing a generic handler for managing console-based user input and actions.
 * Provides the structure for processing commands and running the application loop.
 */
trait Handler {

  /**
   * Starts the handler, typically initiating the command loop.
   * This method should implement the main logic for continuously accepting and processing user commands.
   */
  def run(): Unit

  /**
   * Processes a single command provided by the user.
   * The implementation should parse the command and execute the appropriate action.
   *
   * @param command The command string entered by the user.
   */
  def processCommand(command: String): Unit
}
