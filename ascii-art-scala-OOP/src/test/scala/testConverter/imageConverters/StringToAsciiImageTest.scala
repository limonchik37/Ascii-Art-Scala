package testConverter.imageConverters

import converters.imageConverters.StringToAsciiImage
import images.AsciiImage
import org.scalatest.funsuite.AnyFunSuite
import pixels.AsciiPixel

class StringToAsciiImageTest extends AnyFunSuite {

  test("StringToAsciiImage should correctly convert a string to an AsciiImage") {
    val input = "ABC\nDEF\nGHI".stripTrailing()

    val converter = new StringToAsciiImage()
    val asciiImage = converter.convert(input)

    assert(asciiImage.getWidth == 3)
    assert(asciiImage.getHeight == 3)

    assert(asciiImage.getPixel(0, 0).contains(AsciiPixel('A')))
    assert(asciiImage.getPixel(1, 0).contains(AsciiPixel('B')))
    assert(asciiImage.getPixel(2, 0).contains(AsciiPixel('C')))

    assert(asciiImage.getPixel(0, 1).contains(AsciiPixel('D')))
    assert(asciiImage.getPixel(1, 1).contains(AsciiPixel('E')))
    assert(asciiImage.getPixel(2, 1).contains(AsciiPixel('F')))

    assert(asciiImage.getPixel(0, 2).contains(AsciiPixel('G')))
    assert(asciiImage.getPixel(1, 2).contains(AsciiPixel('H')))
    assert(asciiImage.getPixel(2, 2).contains(AsciiPixel('I')))
  }

  test("StringToAsciiImage should handle empty string") {
    val input = " "

    val converter = new StringToAsciiImage()
    val asciiImage = converter.convert(input)

    assert(asciiImage.getWidth == 1)
    assert(asciiImage.getHeight == 1)
  }

  test("StringToAsciiImage should handle strings with varying line lengths") {
    val input = "ABC\nDE \nGHIJK".stripTrailing()

    val converter = new StringToAsciiImage()
    val asciiImage = converter.convert(input)

    assert(asciiImage.getWidth == 3)
    assert(asciiImage.getHeight == 3)

    assert(asciiImage.getPixel(0, 0).contains(AsciiPixel('A')))
    assert(asciiImage.getPixel(1, 0).contains(AsciiPixel('B')))
    assert(asciiImage.getPixel(2, 0).contains(AsciiPixel('C')))

    assert(asciiImage.getPixel(0, 1).contains(AsciiPixel('D')))
    assert(asciiImage.getPixel(1, 1).contains(AsciiPixel('E')))
    assert(asciiImage.getPixel(2, 1).contains(AsciiPixel(' ')))

    assert(asciiImage.getPixel(0, 2).contains(AsciiPixel('G')))
    assert(asciiImage.getPixel(1, 2).contains(AsciiPixel('H')))
    assert(asciiImage.getPixel(2, 2).contains(AsciiPixel('I')))
  }
}