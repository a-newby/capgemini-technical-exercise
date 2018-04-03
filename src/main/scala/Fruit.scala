
sealed trait Fruit {
  def price: BigDecimal
}

case object Apple extends Fruit {
  def price: BigDecimal = 0.6
}

case object Orange extends Fruit {
  def price: BigDecimal = 0.25
}
