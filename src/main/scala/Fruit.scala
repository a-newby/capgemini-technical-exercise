
sealed trait Fruit {
  def price: Double
}

case object Apple extends Fruit {
  def price: Double = 0.6
}

case object Orange extends Fruit {
  def price: Double = 0.25
}
