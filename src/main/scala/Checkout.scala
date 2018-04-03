
object Checkout {
  def checkout(items: List[Fruit]): BigDecimal = {
    val basePrice = items.map(_.price).sum
    val appleDiscount = BigDecimal(0.6) * (items.count(_ == Apple) / 2)
    val orangeDiscout = BigDecimal(0.25) * (items.count(_ == Orange) / 3)
    (basePrice - appleDiscount) - orangeDiscout
  }
}
