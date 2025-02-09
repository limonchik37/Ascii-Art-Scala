package pixels


case class AsciiPixel( c: Char ) extends Pixel {

  override def getValue: Int = c.toInt
}