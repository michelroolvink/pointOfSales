package pos

import org.scalatest.{Matchers, WordSpec}
import TestData._

import scala.util.{Failure, Success}

class PointOfSalesSuite extends WordSpec with Matchers {

  "A basket with ABCDABA " should {
    "have total price 13.25" in {
      val terminal = new PointOfSales(catalogue)
      terminal.scan("A")
      terminal.scan("B")
      terminal.scan("C")
      terminal.scan("D")
      terminal.scan("A")
      terminal.scan("B")
      terminal.scan("A")
      terminal.calculateTotal() shouldEqual 13.25
    }
  }

 "A basket with CCCCCCC " should {
    "have total price 6.0" in {
      val terminal = new PointOfSales(catalogue)
      terminal.scan("C")
      terminal.scan("C")
      terminal.scan("C")
      terminal.scan("C")
      terminal.scan("C")
      terminal.scan("C")
      terminal.scan("C")
      terminal.calculateTotal() shouldEqual 6.0
    }
  }
  "A basket with ABCD " should {
    "have total price 7.25" in {
      val terminal = new PointOfSales(catalogue)
      terminal.scan("A")
      terminal.scan("B")
      terminal.scan("C")
      terminal.scan("D")
      terminal.calculateTotal() shouldEqual 7.25
    }
  }
  "An empty basket  " should {
    "have total price 0.00" in {
      val terminal = new PointOfSales(catalogue)
      terminal.calculateTotal() shouldEqual 0.0
    }
  }
  "A catalogue without unit value  " should {
    "give Price requires a unit price error" in {
      val thrown = the [IllegalArgumentException] thrownBy new PointOfSales(catalogue2)
      thrown.getMessage should equal ("requirement failed: Not each product has a valid priceList")
    }
  }
  "An catalogue with zero quantity value " should {
    "give Quantities in a Price must be >= 1 error" in {
      val thrown = the [IllegalArgumentException] thrownBy new PointOfSales(catalogue0)
      thrown.getMessage should equal ("requirement failed: Not each product has a valid priceList")
    }
  }
  "A basket with E " should {
    "give key not found error" in {
      val terminal = new PointOfSales(catalogue)
      terminal.scan("E")
      val thrown = the [NoSuchElementException] thrownBy terminal.calculateTotal()
      thrown.getMessage should equal ("key not found: E")
    }
  }
}
