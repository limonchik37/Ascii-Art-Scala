package image_io.input

class GIFImageLoader extends AbstractImageLoader {
  override protected def isSupportedExtension(extension: String): Boolean = {
    extension == "gif"
  }
}