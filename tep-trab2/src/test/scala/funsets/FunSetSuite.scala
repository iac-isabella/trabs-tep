package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/* Aluna: Isabella de Albuquerque Ceravolo */

/** Esta classe implementa um conjunto de testes para os métodos do
  * objeto FunSets. Para executar este conjunto de testes você deve
  * executar o comando `test` no console do SBT.
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /** Link para o scaladoc - um tutorial bastante detalhado e claro
    * sobre FunSuite (em inglês).
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Veja os operadores:
    * - test
    * - ignore
    * - pending
    *
    * Consulte também o exemplo de testes dado no Trabalho 0.
    */

  import FunSets._

  test("contains foi implementado") {
    assert(contains(x => true, 100)) // Você sabe que conjunto é
                                     // definido por `x => true`?
  }

  /** Ao escrever testes é muito comum querer reusar certos valores para
    * múltiplos testes. Por exemplo, pode-se querer criar um IntSet e
    * fazer vários testes como ele.
    *
    * Ao invés de copiar e colar o código para criar os valores em
    * cada testes, pode-se armazenar os valores na classe de testes
    * usando definições `val`:
    *
    * val s1 = singletonSet(1)
    *
    * Entretanto, o que acontece se o método `singletonSet` tiver um
    * erro (bug) e abortar? Nesse caso os testes não serão nem
    * executados, porque a criação dos valores irá falha!
    *
    * Assim, é melhor colocar a criação dos valores para testes dentro
    * de um `trait` separado e criar uma instância do `trait` em cada
    * teste. Há um exemplo abaixo de como fazer isso.
    */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
	val s4 = singletonSet(1)
	def f = (x: Int) => true
	def par = (y: Int) => y % 2 == 0
	def maior = (y: Int) => y < y + 1
	def complexo = (y: Int) => y * y == -1
	def quadrado = (y: Int) => y * y
  }

  /** Este teste está desabilitado (usando a função `ignore`) porque o
    * método `singletonSet` ainda não foi implementado e o teste iria
    * falhar.
    *
    * Quando você terminar de implementar o `singletonSet`, troque a
    * função `ignore` por `test` para executar o teste.
    */
  test("singletonSet(1) contains 1") {

    /** Ao criar uma nova instância do trait `TestSets`, ganha-se acesso
      * ao valores `s1`, `s2` e `s3` que podem ser usados no teste.
      */
    new TestSets {
      assert(contains(s1, 1), "Singleton(1)")
      assert(contains(s2, 2), "Singleton(2)")
      assert(contains(s3, 3), "Singleton(3)")
    }
  }

  // Escreva o restante dos testes.
  
  // Teste Union
  test("Union Tests") {
    new TestSets {
      assert(union(s1, s2)(1), "s1 union s2 contains 1")
      assert(union(s1, s1)(1), "s1 union s1 contains 1")
	  assert(!union(s1, s2)(3), "s1 union s2 not contains 3")
    }
  }
  
  // Teste Intersect
  test("Intersect Tests") {
    new TestSets {
      assert(intersect(s1, s4)(1), "s1 intersect s4 contains 1")
      assert(intersect(s1, s1)(1), "s1 intersect s1 contains 1")
	  assert(!intersect(s1, s2)(1), "s1 intersect s2 not contains 1")
    }
  }
  
  // Teste Diff
  test("Difference Tests") {
    new TestSets {
      assert(diff(s1, s2)(1), "s1 diff s2 contains 1")
      assert(!diff(s1, s1)(1), "s1 diff s1 not contains 1")
	  assert(!diff(s1, s4)(1), "s1 diff s4 not contains 1")
    }
  }
  
  // Teste Filter
  test("Filter Tests") {
    new TestSets {
      assert(!filter(f, par)(1), "f filter (y: Int) => y % 2 == 0 not contains 1")
      assert(filter(f, par)(2), "f filter (y: Int) => y % 2 == 0 contains 2")
    }
  }
  
  // Teste Forall
  test("Forall Tests") {
    new TestSets {
      assert(!forall(f, par), "(y: Int) => y % 2 == 0 is not true for all f")
      assert(forall(f, maior), "(y: Int) => y < y + 1 is true for all f")
    }
  }
  
  // Teste Exists
  test("Exists Tests") {
    new TestSets {
      assert(!exists(f, complexo), "(y: Int) => y * y == -1 not exists for f")
      assert(exists(f, maior), "(y: Int) => y < y + 1 exists in f")
    }
  }
  
  // Teste Map
  test("Map Tests") {
    new TestSets {
      assert(!contains(map(f, quadrado), -1), "map(f, quadrado) not contains -1")
      assert(contains(map(f, quadrado), 1), "map(f, quadrado) contains 1")
    }
  }

}
