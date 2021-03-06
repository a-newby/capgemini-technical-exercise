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
      val expectedOutput = BigDecimal(0.6) * (appleList.length / 2 + appleList.length % 2)
      val testOutput = Checkout.checkout(appleList)
      assert(testOutput == expectedOutput)
    }
  }

  it should "only charge two thirds of the Oranges, rounded up, due to three-for-the-price-of-two" in {
    forAll(Gen.listOf(Orange)) { orangeList =>
      val expectedOutput = BigDecimal(0.25) * ((orangeList.length / 3) * 2 + (orangeList.length % 3))
      val testOutput = Checkout.checkout(orangeList)
      assert(testOutput == expectedOutput)
    }
  }

  it should "apply both deals to lists of mixed fruit" in {
    forAll(Gen.listOf(Gen.oneOf(Apple, Orange))) { fruitList =>
      val apples = fruitList.count(_ == Apple)
      val oranges = fruitList.count(_ == Orange)
      val expectedOutput =
        (BigDecimal(0.6) * (apples / 2 + apples % 2)) + (BigDecimal(0.25) * ((oranges / 3) * 2 + (oranges % 3)))
      val testOutput = Checkout.checkout(fruitList)
      assert(testOutput == expectedOutput)
    }
  }
}