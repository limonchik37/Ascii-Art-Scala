```markdown
# ASCII Art Generator in Scala

This is a console-based application built with Scala that converts images into ASCII art. The project also supports optional filters and customization for the output.

## Features

- **Image Conversion**: Transform PNG, JPEG, or GIF images into ASCII art.
- **Custom Filters**: Apply filters to modify the image before conversion.
- **Random Image Generation**: Create random images and convert them into ASCII.
- **Console Interaction**: Easy-to-use console-based interface for managing operations.

## How to Run

### Prerequisites
- **Scala**: Ensure Scala is installed on your system. [Download Scala](https://www.scala-lang.org/download/)
- **SBT**: Make sure the latest version of SBT is installed. [Install SBT](https://www.scala-sbt.org/download.html)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ascii-art-scala.git
   cd ascii-art-scala
   ```

2. Build the project:
   ```bash
   sbt compile
   ```

3. Run the application:
   ```bash
   sbt run
   ```

4. Follow the console prompts to load an image, apply filters, and generate ASCII art.

## Usage Examples

### Convert an Image to ASCII Art
1. Place your image in the `resources` folder.
2. Select the image via the console interface.
3. View the ASCII art in the console or export it as a text file.

### Apply Filters
- Filters such as grayscale, inversion, or edge detection can be applied before conversion.

### Generate Random Images
1. Use the "Random Image" feature in the console.
2. View the generated ASCII art.

## Future Enhancements
- Add support for colorized ASCII art.
- Implement a GUI for easier interaction.
- Support additional image formats.

## Contributing
Contributions are welcome! Feel free to submit issues or pull requests.
