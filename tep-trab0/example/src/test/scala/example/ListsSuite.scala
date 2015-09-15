package example

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * Esta class implementa um conjunto de testes ScalaTest para os
  * metodos do objeto 'Lists' que precisa ser implementado como parte
  * de um trabalho de programacao. Um conjunto de testes (test suit) eh
  * simplesmente um conjunto de testes individuais para algum
  * componente especifico de um programa.
  *
  * Um test suit eh criado definindo-se uma classe que estenda a classe
  * 'org.scalatest.FunSuit'. Ao executar a ferramenta ScalaTest ela
  * ira automaticamente encontrar estas classes e executar todos os
  * seus testes.
  *
  * Para executar este conjunto de testes, inicie o console do sbt e
  * execute o comando "test".
  */
@RunWith(classOf[JUnitRunner])
class ListsSuite extends FunSuite {

  /**
    * Testes sao escritos usando-se o operador 'test', que recebe dois
    * argumentos:
    *
    * - Uma descricao textual do teste. Esta descricao tem que ser
    *   unica para cada teste. Ou seja, nao pode haver dois ou mais
    *   testes com a mesma descricao.
    * - O corpo do testes, composto por codigo Scala que implementa o
    *   teste.
    *
    * A forma mais comum de implementar o corpo do teste eh
    * utilizando-se o metodo 'assert' que testa se o seu argumento eh
    * 'true'. Entao, um dos testes mais simples possivel eh o seguinte:
    */
  test("one plus one is two"){assert((1 + 1)== 2)}


  /**
    * Em Scala, eh permitido passar um argumento a um metodo
    * utilizando-se a sintaxe de bloco, i.e., '{ argumento }' ao inves
    * de parenteses.
    *
    * Isso permite escrever os testes de uma forma mais legivel:
    */
  test("one plus one is three?") {
    assert((1 + 1) == 2) // Essa assertiva falha! Va em frente e a concerte.
  }


  /**
    * Um problema com a abordagem do teste anterior eh que o ScalaTest
    * vai apenas dizer que um teste falhor, mas nao vai dizer qual foi
    * a razao da falha. A saida produzida pelo ScalaTest tera esta
    * forma:
    *
    * {{{
    *    [info] - one plus one is three? *** FAILED ***
    * }}}
    *
    * Esta situacao pode ser um pouco melhorada usando-se o operador
    * especial de igualdade '===' ao inves do operador normal '=='
    * (isso so eh possivel usando o ScalaTest). Entao, se voce executar
    * o teste a seguir, o ScalaTest vai mostrar a saida abaixo:
    *
    * {{{
    *    [info] - details why one plus one is not three *** FAILED ***
    *    [info]   2 did not equal 3 (ListsSuite.scala:67)
    * }}}
    *
    * Eh recomendavel usar sempre o operador de igualdade '===' ao
    * escrever testes.
    */
  test("details why one plus one is not three") {
    assert((1 + 1) === 2) // Fix me, please!
  }


  /**
    * Para permitir testar os comportamentos de excecao de um metodo,
    * o ScalaTest oferece a operacao 'intercept'.
    *
    * No exemplo abaixo, nos testamos o fato de que o metodo
    * 'intNotZero' dispara a exceção 'IllegalArgumentException' se o
    * seu argumento for '0'.
    */
  test("intNotZero throws an exception if its argument is 0") {
    intercept[IllegalArgumentException] {
      intNotZero(0)
    }
  }

  def intNotZero(x: Int): Int = {
    if (x == 0) throw new IllegalArgumentException("zero is not allowed")
    else x
  }


  /**
    * Agora nos finalmente podemos escrever alguns testes para as
    * funcoes the listas que tem que ser implementadas neste trabalho
    * de programacao. Primeiramente nos precisamos importar todos os
    * metodos do objeto 'Lists'.
    */
  import Lists._


  /**
    * Sao fornecidos apenas dois testes bastante basicos abaixo. Voce
    * deve escrever mais testes para ter certeza que os métodos 'sum'
    * e 'max' funcionam como esperado.
    *
    * Em particular, escreva testes para os casos de contorno: numeros
    * negativos, zeros, listas vazias, listas com elementos repetidos,
    * etc.
    *
    * Eh permitido ter multiplas declaracoes 'assert' dentro de um
    * mesmo teste, entretanto, eh recomendado escrever uma declaracao
    * 'test' individual para cada aspecto testado em um método.
    */

  /*
  Testes da funcao sum
   */
  test("sum of a few numbers") {
    assert(sum(List(1,2,0)) === 3)
  }

  test("soma de numeros negativos") {
    assert(sum(List(-1,-2,-3)) === -6)
  }

  test("soma de lista vazia") {
    assert(sum(List()) === 0)
  }

  test("soma de elementos repetidos") {
    assert(sum(List(1,1,1)) === 3)
  }

  /*
  Testes da funcao max
   */
  test("max of a few numbers") {
    assert(max(List(3, 7, 2)) === 7)
  }

  test("maximo de numeros negativos") {
    assert(max(List(-1, -5, -2)) === -1)
  }

  test("maximo de lista vazia") {
    intercept[NoSuchElementException]{max(List())}
  }

  test("maximo de numeros repetidos") {
    assert(max(List(-1, -2, -2)) === -1)
  }

}
