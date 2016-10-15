package pos

import pos.PointOfSales.{Catalogue, Price}

/**
  * Created by ruud on 12/10/2016.
  */
object TestData {

  val priceListA = List(Price(1, BigDecimal(1.25)), Price(3, BigDecimal(3.0)))
  val priceListB = List(Price(1, BigDecimal(4.25)))
  val priceListC = List(Price(1, BigDecimal(1.0)), Price(6, BigDecimal(5.0)))
  val priceListD = List(Price(1, BigDecimal(0.75)))
  val priceList2 = List(Price(2, BigDecimal(0.75)))
  val priceList0 = List(Price(0, BigDecimal(0.75)))

  val catalogue: Catalogue = Map(
    "A" -> priceListA,
    "B" -> priceListB,
    "C" -> priceListC,
    "D" -> priceListD)

  val catalogue2 = Map(
    "A" -> priceList2)

  val catalogue0 = Map(
    "A" -> priceList0)
}
