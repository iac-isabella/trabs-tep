package recfun
import common._

object Main {

  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercício 1
   */
  def pascal(c: Int, r: Int): Int = ???

  /**
   * Exercício 2
   */
  def balance(chars: List[Char]): Boolean = ???

  /**
   * Exercício 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}