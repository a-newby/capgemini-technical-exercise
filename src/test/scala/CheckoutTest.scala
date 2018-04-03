import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.prop.PropertyChecks

final class CheckoutTest extends FlatSpec with PropertyChecks {
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

  it should "return 0.25 for a list with an Orange" in {
    val expectedOutput = 0.25
    val testOutput = Checkout.checkout(List[Fruit](Orange))
    assert(testOutput == expectedOutput)
  }

  it should "return 0.85 for a list with an Apple and an Orange" in {
    val expectedOutput = 0.85
    val testOutput = Checkout.checkout(List[Fruit](Apple, Orange))
    assert(testOutput == expectedOutput)
  }

  /* NOTE: There was a simpler property test here, but it was invalidated by the buy-one-get-one-free deal*/

  it should "only charge for half the Apples, rounded up, due to buy-one-get-one-free" in {
    forAll(Gen.listOf(Apple)) { appleList =>
      val expectedOutput = 0.6 * BigDecimal(appleList.length / 2 + appleList.length % 2)
      val testOutput= Checkout.checkout(appleList)
      assert(testOutput == expectedOutput)
    }
  }
}