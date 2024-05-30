object UserAPI extends App {
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
