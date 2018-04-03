
object Checkout {
  def checkout(items: List[Fruit]): BigDecimal = {
    val basePrice = items.map(_.price).sum
    val appleDiscount = BigDecimal(0.6) * (items.count(_ == Apple) / 2)
    basePrice - appleDiscount
  }
}
