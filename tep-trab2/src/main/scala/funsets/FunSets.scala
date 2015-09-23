package funsets

import common._

/* Aluna: Isabella de Albuquerque Ceravolo */

/**
 * 2. Conjuntos funcionais puros.
 */
object FunSets {
  /** Um conjunto é representado por sua função característica, i.e., o
    * seu predicado.
    */
  type Set = Int => Boolean

  /** Indica se um conjunto contém ou não um elemento.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /** Retorna o conjunto que contém exatamente o elemento indicado.
    */
  def singletonSet(elem: Int): Set = item => item == elem
  
  /* Definicao auxiliar para ajudar a criar uma nocao de conjunto nas funcoes seguintes... */
  def vazio(elem: Int) : Set = item => false

  /** Retorna a união dos dois conjuntos dados, i.e., o conjunto de
    * todos os elementos que estão em `s` ou em `t`.
    */
  def union(s: Set, t: Set): Set = item => s(item) || t(item)

  /** Retorna a interseção dos dois conjuntos dados, i.e., o conjunto
    * contendo todos os elementos que estão em `s` e em `t`.
    */
  def intersect(s: Set, t: Set): Set = item => s(item) && t(item)

  /** Retorna a diferença entre os dois conjuntos dados, i.e., o
    * conjunto contendo todos os elementos que estão em `s` e não estão
    * em `t`.
    */
  def diff(s: Set, t: Set): Set = item => s(item) && (t(item) == false)

  /** Retorna o subconjunto de `s` para o qual `p` é verdadeiro.
    */
  def filter(s: Set, p: Int => Boolean): Set = item => {if(p(item)) s(item) else false}

  /** Os limites para os testes das funções `forall` e `exists`
    * são +/- 1000.
    */
  val bound = 1000

  /** Retorna `true` se *todos* os inteiros (entre os limites
    * especificados por `bound`) dentro de `s` satisfazem o predicado
    * `p`; e `false` caso contrário.
    */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (???) ???
      else if (???) ???
      else iter(???)
    }
    iter(???)
  }

  /** Retorna `true` se *algum* dos inteiros (entre os limites
    * especificados por `bound`) dentro de `s` satisfazem o predicado
    * `p`; e `false` caso contrário.
    */
  def exists(s: Set, p: Int => Boolean): Boolean = ???

  /** Retorna um conjunto transformado pela aplicação de `f` a cada
   * elemntos do conjunto `s`.
   */
  def map(s: Set, f: Int => Int): Set = ???

  /** Exibe o conteúdo de um conjunto -- dentro dos limites definidos
    * por `bound`.
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /** Imprime o conteúdo de um conjunto no console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }
}
