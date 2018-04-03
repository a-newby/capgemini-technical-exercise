
object Checkout {
  def checkout(items: List[Fruit]): Double = items.map(_.price).sum
}
