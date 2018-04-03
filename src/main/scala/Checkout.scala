
object Checkout {
  def checkout(items: List[Fruit]): BigDecimal = items.map(_.price).sum
}
