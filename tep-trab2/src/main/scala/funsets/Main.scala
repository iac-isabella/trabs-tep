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
  println("Is 2 in s(2) filter x < 2 ?")
  println(filter(singletonSet(2), (x: Int) => x < 2)(2))
  println("Is 2 in s(2) filter x > 0 ?")
  println(filter(singletonSet(2), (x: Int) => x > 0)(2))
  println("n % 2 == 0 for 'all' integers?")
  println(forall((x: Int) => x == x , (n: Int) => n % 2 == 0))
  println("n % 2 == 0 exists in 'all' integers?")
  println(exists((x: Int) => x == x , (n: Int) => n % 2 == 0))
  println("map x * x for all integers contains 1 ?")
  println(contains(map((x: Int) => x == x, (y: Int) => y * y), 1))
}
