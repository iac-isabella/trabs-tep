package funsets

/* Aluna: Isabella de Albuquerque Ceravolo */

/* Tente melhorar o programa principal.
 */
object Main extends App {
  import FunSets._
  println("contains(singletonSet(1), 1)")
  println(contains(singletonSet(1), 1))
  println("contains(singletonSet(1), 2)")
  println(contains(singletonSet(1), 2))
  println("(contains(vazio(1), 1))")
  println((contains(vazio(1), 1)))
  println("s(1) union t(2) contains 1 ?")
  println(union(singletonSet(1), singletonSet(2))(1))
  println("s(1) intersect t(2) contains 1 ?")
  println(intersect(singletonSet(1), singletonSet(2))(1))
  println("s(1) diff t(1) contains 1 ?")
  println(diff(singletonSet(1), singletonSet(1))(1))
  println(filter(singletonSet(2), (x: Int) => x < 2)(2))
  println(filter(singletonSet(2), (x: Int) => x > 0)(2))
}
