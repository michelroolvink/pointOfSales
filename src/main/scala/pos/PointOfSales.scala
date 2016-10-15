package pos

import pos.PointOfSales._

import scala.util.{Failure, Try}

object PointOfSales {

  type Product = String // just the name of the product

  case class Price(quantity: Int, price: BigDecimal) extends Ordered[Price] {
    override def compare(that: Price): Int = (this.price - that.price).toInt
  } // (quantity, price), the price for a given quantity, to allow discounts for larger quantities

  type PriceList = List[Price]

  type Catalogue = Map[Product, PriceList] // the PriceList for each product

  type Basket = Map[Product, Int] // the scanned quantity per Product

  def addProduct2Basket(product: Product, basket: Basket): Basket = {
    basket.get(product) match {
      case Some(quantity) => basket + (product -> (quantity + 1))
      case None => basket + (product -> 1)
    }
  }

  private def calculateArticlePrice(quantity: Int, priceList: PriceList): BigDecimal = {
    def accCalc(quantity: Int, priceList: PriceList, acc: BigDecimal): BigDecimal = priceList match {
      case Nil => acc
      case Price(qty, prc) :: rest => accCalc(quantity % qty, rest, acc + prc * (quantity / qty))
    }
    accCalc(quantity, priceList.sorted.reverse, BigDecimal(0.0))
  }

  private def calculateBasketPrice(basket: Basket, catalogue: Catalogue): BigDecimal = {
    val articlePrices = for {
      (product, quantity) <- basket
      articlePrice = calculateArticlePrice(quantity, catalogue(product))
    } yield articlePrice
    articlePrices.sum
  }
}

class PointOfSales(catalogue: Catalogue) {
  require(catalogue.forall {
    case (product, priceList) => priceList.sorted.head.quantity == 1
  }, "Not each product has a valid priceList")

  private var basket: Basket = Map.empty

  def scan(product: Product): Unit = basket = addProduct2Basket(product, basket)

  def calculateTotal(): BigDecimal = calculateBasketPrice(basket, catalogue)
}
