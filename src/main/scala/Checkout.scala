val a: Product = Product("A", 80)
val b: Product = Product("B", 35)
val c: Product = Product("C", 20)
val d: Product = Product("D", 10)

val prices: Deals = WorkerAPI.deals

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

class Checkout(deals: Deals, cart: Map[Product, Int] = Map()) {
  def scan(product: Product): Checkout = {
    val addItem = cart.updatedWith(product) {
      case Some(value) => Some(value + 1)
      case None => Some(1)
    }
    new Checkout(deals, addItem)
  }
  def getCart: Map[Product, Int] = cart

  def cancelScan(product: Product): Checkout = {
    cart.get(product) match
      case Some(value) =>
        new Checkout(deals,
          if (value == 1) cart.removed(product)
          else cart.updated(product, value - 1))
      case None =>
        new Checkout(deals, cart)
  }

  def total: Int = {
    cart.map { (product, amount) =>
      deals.getDeals.get(product) match

        case Some(value) =>
          def calcEachDeal(list: List[(Int, Int)], tempAmount: Int, acc: Int = 0): Int = list match
            case Nil if tempAmount != 0 => product.price * tempAmount + acc
            case Nil => acc
            case (buy, pay) :: tail if tempAmount >= buy => calcEachDeal(list, tempAmount - buy, pay + acc)
            case (buy, pay) :: tail => calcEachDeal(tail, tempAmount, acc)
          calcEachDeal(value, amount)
        case None => product.price * amount

    }.sum
  }

}
