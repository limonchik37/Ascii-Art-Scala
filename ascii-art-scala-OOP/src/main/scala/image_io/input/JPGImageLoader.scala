package image_io.input

class JPGImageLoader extends AbstractImageLoader {
  override protected def isSupportedExtension(extension: String): Boolean = {
    extension == "jpg" || extension == "jpeg"
  }
}