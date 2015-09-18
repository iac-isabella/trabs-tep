package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

  /**
	* Aluna: Isabella de Albuquerque Ceravolo
	*/

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList) === true)
  }

  // ==================================================================
  // Escreva mais testes.
  // ==================================================================
  
  test("balance: ':-)' is not balanced") {
    assert(balance(":-)".toList) === false)
  }
  
  test("balance: '())(' is not balanced") {
    assert(balance("())(".toList) === false)
  }
  
  test("balance: 'hello' is balanced") {
    assert(balance("hello".toList) === true)
  }
  
}
