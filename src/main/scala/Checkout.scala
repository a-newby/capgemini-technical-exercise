
object Checkout {
  def checkout(items: List[Fruit]): Double = items.headOption.map(_.price).getOrElse(0.0)
}
