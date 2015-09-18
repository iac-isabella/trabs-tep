package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

  /**
	* Aluna: Isabella de Albuquerque Ceravolo
	*/

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {

  import Main.pascal

  test("pascal: col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  // ==================================================================
  // Escreva mais testes.
  // ==================================================================

  test("pascal: col=2,row=2") {
    assert(pascal(2,2) === 1)
  }
  
  test("pascal: col=-2,row=-2") {
    assert(pascal(-2,-2) === 0)
  }
  
  test("pascal: col=2,row=4") {
    assert(pascal(2,4) === 6)
  }
  
}
