object Main {
  def main(args: Array[String]): Unit = {
    val prices: Deals = Deals()
      .addDeal(a, 3, 220)
      .addDeal(a, 5, 350)
      .addDeal(b, 2, 55)
    
    val checkout = Checkout(prices)
      .scan(a)
      .scan(a)
      .scan(a)
      .scan(a)
      .scan(a)

      .scan(a)
      .scan(a)
      .scan(a)

      .scan(a)
      .scan(b)
      .scan(c)
      .cancelScan(b)
      .total

    println(checkout)
  }
}