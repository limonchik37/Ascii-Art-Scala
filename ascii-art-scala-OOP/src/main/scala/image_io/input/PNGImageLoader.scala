package image_io.input

class PNGImageLoader extends AbstractImageLoader {

  override protected def isSupportedExtension(extension: String): Boolean = {
    extension == "png"
  }
}