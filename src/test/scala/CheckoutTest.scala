import org.scalatest.FlatSpec

final class CheckoutTest extends FlatSpec {
  "The checkout" should "return 0.0 for an empty input list" in {
    val expectedOutput = 0.0
    val testOutput = Checkout.checkout(List.empty)
    assert(testOutput == expectedOutput)
  }

  it should "return 0.6 for a list with an Apple" in {
    val expectedOutput = 0.6
    val testOutput = Checkout.checkout(List[Fruit](Apple))
    assert(testOutput == expectedOutput)
  }
}