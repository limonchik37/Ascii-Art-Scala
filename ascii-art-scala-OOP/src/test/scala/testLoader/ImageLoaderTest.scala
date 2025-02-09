package testLoader

import image_io.input.{GIFImageLoader, JPGImageLoader, PNGImageLoader}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfter
import images.RGBImage

import java.io.File

class ImageLoaderTest extends AnyFunSuite with BeforeAndAfter {

  val resourcesPath = "C:\\program1\\oop\\ascii-art-scala-OOP\\src\\test\\resources"

  test("GIFImageLoader should load a valid GIF image") {
    val gifLoader = new GIFImageLoader()
    val gifImagePath = s"$resourcesPath/image.gif"

    val image = gifLoader.loadImage(gifImagePath)

    assert(image.isInstanceOf[RGBImage])
    assert(new File(gifImagePath).exists())
  }

  test("GIFImageLoader should throw an exception for unsupported format") {
    val gifLoader = new GIFImageLoader()
    val jpgImagePath = s"$resourcesPath/image.jpg"

    assertThrows[UnsupportedOperationException] {
      gifLoader.loadImage(jpgImagePath)
    }
  }

  test("JPGImageLoader should load a valid JPG image") {
    val jpgLoader = new JPGImageLoader()
    val jpgImagePath = s"$resourcesPath/image.jpg"

    val image = jpgLoader.loadImage(jpgImagePath)

    assert(image.isInstanceOf[RGBImage])
    assert(new File(jpgImagePath).exists())
  }

  test("JPGImageLoader should throw an exception for unsupported format") {
    val jpgLoader = new JPGImageLoader()
    val pngImagePath = s"$resourcesPath/image.png"

    assertThrows[UnsupportedOperationException] {
      jpgLoader.loadImage(pngImagePath)
    }
  }

  test("PNGImageLoader should load a valid PNG image") {
    val pngLoader = new PNGImageLoader()
    val pngImagePath = s"$resourcesPath/image.png"

    val image = pngLoader.loadImage(pngImagePath)

    assert(image.isInstanceOf[RGBImage])
    assert(new File(pngImagePath).exists())
  }

  test("PNGImageLoader should throw an exception for unsupported format") {
    val pngLoader = new PNGImageLoader()
    val gifImagePath = s"$resourcesPath/image.gif"

    assertThrows[UnsupportedOperationException] {
      pngLoader.loadImage(gifImagePath)
    }
  }

  test("All loaders should throw an exception for invalid file path") {
    val gifLoader = new GIFImageLoader()
    val invalidPath = s"$resourcesPath/nonexistent.gif"

    assertThrows[IllegalArgumentException] {
      gifLoader.loadImage(invalidPath)
    }
  }
}