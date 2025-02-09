package AsciiArtApp.console.appInterface.pageAppInterface

import AsciiArtApp.console.appInterface.ConsoleTextPage

class GuidePage extends ConsoleTextPage { // todo object

  override def load(): String = {
    var result = ""

    result += "Usage:\n"
    result += "  --image-random               : Load a random image\n"
    result += "  --image [path]               : Load an image from the given path\n"
    result += "  --flip [value]               : Flip the image (values: x, y, xy)\n"
    result += "  --invert           : Rotate the image by the given degrees. Degrees can be [90,180,270]\n"
    result += "  --brightness [value]              : Scale the image by the given value. Value can be [0.25,1,4]\n"
    result += "  --nonlinear-ascii            : Convert current image to nonlinear ASCII \n"
    result += "  --custom-table [palette]     : Set the ASCII palette for linear conversion\n"
    result += "  --linear-ascii               : Convert current image to nonlinear ASCII \n"
    result += "  --output-console             : Display the image in the console\n"
    result += "  --output-file [path]         : Save the image to the specified path\n"
    result += "  --exit                       : Close the app\n"
    result += "\n"
    result
  }
}