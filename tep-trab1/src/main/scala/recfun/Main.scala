package recfun
import common._

  /**
	* Aluna: Isabella de Albuquerque Ceravolo
	*/

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
  def pascal(c: Int, r: Int): Int = {
	if(c < 0 || r < 0) { 
		0 
	}
	else {
		if(c == 0 || c == r) 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)
	}
  }

  /**
   * Exercício 2
   */
  def balance(chars: List[Char]): Boolean = {
	def contar(lista: List[Char], contador: Int): Boolean = {
		if(lista.isEmpty && contador == 0) {
			true
		}
		else {
			if((lista.isEmpty && contador != 0) || (contador < 0)) {
				false
			}
			else{
				if(lista.head == '(') {
					contar(lista.tail, contador + 1)
				}
				else {
					if(lista.head == ')') {
						contar(lista.tail, contador - 1)
					}
					else {
						contar(lista.tail, contador)
					}
				}
			}
		}
	}
	contar(chars, 0)
  }

  /**
   * Exercício 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
	if((coins.isEmpty && money > 0) || (money < 0))  {
		0
	}
	else {
		if(money == 0) {
			1
		}
		else {
			countChange(money, coins.tail) + countChange(money - coins.head, coins)
		}
	}
  }
}
