package testAppInterface

import AsciiArtApp.console.controllers.ConsoleController
import converters.AsciiConverters.ConversionType
import facade.ImageProcessing
import image_io.input.ImageLoader
import images.{AsciiImage, RGBImage}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.mockito.{ArgumentCaptor, ArgumentMatchers}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import palettes.CustomAsciiArtSettings
import pixels.ImagePixel

class ConsoleControllerTest extends AnyFunSuite with MockitoSugar {

  test("loadRandomImage should call facade to load a random image") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    controller.loadRandomImage()
    verify(mockFacade, never()).loadImageFromPath(any(), any())
  }

  test("loadImage should call facade to load an image from a path") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    val testPath = "C:\\program1\\oop\\ascii-art-scala-OOP\\src\\test\\resources\\test.jpg"

    controller.loadImage(testPath)

    verify(mockFacade).loadImageFromPath(any[String], any[ImageLoader[String, ImagePixel]])
  }



  test("convertImageToLinearAscii should set the conversion strategy to linear") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    controller.currentImage = Some(mock[RGBImage])
    controller.convertImageToLinearAscii()

    assert(controller.current_converter.contains(ConversionType.Linear))
    verify(mockFacade, never()).linearConversion(any())
  }

  test("changeBrightness should call facade to adjust brightness") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    val mockImage = mock[RGBImage]
    controller.currentImage = Some(mockImage)

    controller.changeBrightness(50)
    verify(mockFacade).brightness(mockImage, 50)
  }

  test("rotateImage should call facade to rotate the image") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    val mockImage = mock[RGBImage]
    controller.currentImage = Some(mockImage)

    controller.rotateImage(90)
    verify(mockFacade).rotate(mockImage, 90)
  }

  test("InvertImage should call facade to invert the image colors") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    val mockImage = mock[RGBImage]
    controller.currentImage = Some(mockImage)

    controller.InvertImage()
    verify(mockFacade).invert(mockImage)
  }


  test("saveImageToFile should call facade to save the ASCII image to file") {
    val mockFacade = mock[ImageProcessing]
    val mockSettings = mock[CustomAsciiArtSettings]
    val controller = new ConsoleController(mockFacade, mockSettings)

    val mockImage = mock[RGBImage]
    controller.currentImage = Some(mockImage)

    when(mockFacade.linearConversion(mockImage)).thenReturn("ASCII ART STRING")

    val testPath = "output.txt"

    controller.saveImageToFile(testPath)

    val asciiImageCaptor = ArgumentCaptor.forClass(classOf[AsciiImage])
    val pathCaptor = ArgumentCaptor.forClass(classOf[String])

    verify(mockFacade).saveIntoFile(asciiImageCaptor.capture(), pathCaptor.capture())

    assert(pathCaptor.getValue == testPath)
    assert(asciiImageCaptor.getValue != null)
  }
}