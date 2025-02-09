package pixels

case class ImagePixel(var red: Int, var green: Int, var blue: Int) extends Pixel {
  require(red >= 0 && red <= 255,
    s"Invalid red value: $red. It should be between 0 and 255."
  )
  require(green >= 0 && green <= 255,
    s"Invalid red value: $green. It should be between 0 and 255."
  )
  require(blue >= 0 && blue <= 255,
    s"Invalid red value: $blue. It should be between 0 and 255."
  )
  override def getValue: Int = red<<16 | green << 8 | blue
}