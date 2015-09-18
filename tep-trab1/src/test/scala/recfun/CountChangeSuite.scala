package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

  /**
	* Aluna: Isabella de Albuquerque Ceravolo
	*/

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {

  import Main.countChange

  test("countChange: exemplo dado nas instruções") {
    assert(countChange(4,List(1,2)) === 3)
  }

  // ==================================================================
  // Escreva mais testes.
  // ==================================================================

  test("countChange: money = 2 and coins = {1, 2} ") {
    assert(countChange(2,List(1,2)) === 2)
  }
  
  test("countChange: money = 2 and coins = {} ") {
    assert(countChange(2,List()) === 0)
  }
  
  test("countChange: money = -1 and coins = {1} ") {
    assert(countChange(-1,List(1)) === 0)
  }
  
}
