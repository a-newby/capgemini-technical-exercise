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

  /*
    NOTE: writing these tests led naturally to property testing, though in the real world one
    argue that this function isn't complex enough to warrant its own module at all, the test did
    helpfully reveal some problems with rounding errors that my original solution had, prompting
    a switch from Double to BigDecimal!
   */
  it should "return the sum of the apple prices and orange prices for an arbitrary list" in {
    forAll(Gen.listOf(Gen.oneOf(Apple, Orange))) { fruitList =>
      val expectedOutput =
        (0.6 * BigDecimal(fruitList.count(_ == Apple))) + (0.25 * BigDecimal(fruitList.count(_ == Orange)))
      val testOutput= Checkout.checkout(fruitList)
      assert(testOutput == expectedOutput)
    }
  }
}