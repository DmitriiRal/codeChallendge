class Deals(deals: Map[Product, List[(Int, Int)]] = Map()) {
  def addDeal(product: Product, buyAmount: Int, pay: Int): Deals =
    val update = deals.updatedWith(product) {
      case Some(value) =>
        val list = (buyAmount, pay) :: value
        val listSorted = list.sortBy((buy, pay) => buy).reverse
        Some(listSorted)
      case None =>
        Some(List((buyAmount, pay)))
    }
    new Deals(update)

  def getDeals: Map[Product, List[(Int, Int)]] = deals
}