package objsets

import common._
import TweetReader._

/**
  * Aluna: Isabella de Albuquerque Ceravolo
  */

/**
  * Classe que representa tweets.
  */
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
    "Text: " + text + " [" + retweets + "]"
}

/**
  * Classe que representa um conjunto de objetos do tipo `Tweet` na
  * forma de uma árvore binária de busca. Cada ramo na árvore tem dois
  * filhos (dois `TweetSet`s). Existe uma invariante que é sempre
  * verdadeira: para cada ramo `b`, todos os elementos na sub-árvore
  * esquerda são menores que o tweet na raiz de `b`. Os elementos na
  * sub-árvore direita são maiores.
  * 
  * Note que a estrutura acima exige que nós sejamos capazes de
  * comparar tweets (nós precisamos poder dizer qual dentre dois
  * tweets é menor, maior ou se são iguais). Nesta implementação, a
  * igualdade ou ordem dos tweets é baseada no texto do
  * tweet. Portanto, um `TweetSet` não pode conter dois tweets com
  * exatamente o mesmo texto de dois usuários diferentes.
  * 
  * A vantagem de representar conjuntos como árvores binárias de
  * busca é que os elementos no conjunto podem ser pesquisados
  * rapidamente. Se você quiser aprender mais sobre esse assunto, dê
  * uma olhada na página da Wikipédia sobre árvores binárias de
  * pesquisa [1,2].
  *
  * [1] http://en.wikipedia.org/wiki/Binary_search_tree
  * [2] http://pt.wikipedia.org/wiki/%C3%81rvore_bin%C3%A1ria_de_busca
  */
abstract class TweetSet {

  /** Este método recebe um predicado e retorna um sub-conjunto
    * contendo todos os elementos do conjunto original para os quais o
    * predicado é verdadeiro.
    * 
    * Pergunta: Esse método pode ser implementado aqui, ou deve ser
    * deixado abstrato e implementado nas sub-classes concretas?
  	*
  	* Resposta :)
  	* Acredito que a implementação deve ser deixada para as subclasses, pois um conjunto
  	* com elementos requer que iteremos sobre todos os elementos, enquanto um conjunto
  	* vazio deveria retornar apenas ele mesmo. Ou seja, as subclasses não tem o mesmo
  	* comportamento.
    */ 
  def filter(p: Tweet => Boolean): TweetSet 

  /** Este é um método auxiliar para `filter` que propaca os tweets
    * acumulados.
    */
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  /** Retorna um novo `TweetSet` que é a união dos conjuntos `this` e
    * `that`.
    * 
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
  	*
  	* Resposta :)
  	* A implementação deve ser deixada para as subclasses, pois 
  	* as subclasses não tem o mesmo comportamento.
    */
   def union(that: TweetSet): TweetSet

  /** Retorna o tweet neste conjunto que tem a maior contagem de
    * retweets.
    * 
    * Chamar `mostRetweeted` em um conjunto vazio deve gerar uma
    * exceção do tipo `java.util.NoSuchElementException`.
    * 
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
  	*
  	* Resposta :)
  	* A implementação deve ser deixada para as subclasses, pois 
  	* as subclasses não tem o mesmo comportamento.
    */
  def mostRetweeted: Tweet

  /** Retorna uma lista contendo todos os tweets deste conjunto,
    * ordenados pela contagem de retweets em ordem
    * decrescente -- i.e., o primeiro é o tweet com mais retweets e o
    * último é o com menos.
    * 
    * Dica: o método `remove` será muito útil. :-)
    * 
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
  	*
  	* Resposta :)
  	* A implementação deve ser deixada para as subclasses, pois 
  	* as subclasses não tem o mesmo comportamento.
    */
  def descendingByRetweet: TweetList


  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  /** Retorna um novo `TweetSet` que contém todos os elementos deste
    * conjunto, e inclui o novo elemento `tweet` caso este ainda não
    * exista no conjunto original.
    * 
    * Se `this contains tweet` for verdadeiro o conjunto original é
    * retornado.
    */
  def incl(tweet: Tweet): TweetSet

  /** Retorna um novo `TweetSet` que exclui `tweet`.
    */
  def remove(tweet: Tweet): TweetSet

  /** Testa se `tweet` existe neste `TweetSet`.
    */
  def contains(tweet: Tweet): Boolean

  /** Este método recebe uma função e aplica esta função a todos os
    * elementos deste conjunto.
    */
  def foreach(f: Tweet => Unit): Unit
}

/** Objeto que representa um conjunto de tweets vazio.
  */
object Empty extends TweetSet {

  def filter(p: Tweet => Boolean): TweetSet = this

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

  def union(that: TweetSet): TweetSet = that

  def mostRetweeted: Tweet = throw new java.util.NoSuchElementException("Nao ha tweet em um conjunto vazio!!!")

  def descendingByRetweet: TweetList = Nil


  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, Empty, Empty)

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()
}

/** Classe que representa um conjunto não-vazio, i.e., contento ao
  * menos um elemento, de tweets.
  */
class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, Empty)

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    if(p(elem)) left.filterAcc(p, right.filterAcc(p, acc.incl(elem)))
    else left.filterAcc(p, right.filterAcc(p, acc))
  }

  def union(that: TweetSet): TweetSet = {
    val set1 = that.incl(elem)
    val set2 = right union set1
    left union set2
  }

  
  def mostRetweeted: Tweet = {
    /**
    * Não ficou muito legivel, mas foi uma ideia que eu acho que poderia ser melhor desenvolvida...
    */
    /*
    val leftMost = left.mostRetweeted
    val rightMost = right.mostRetweeted

    leftMost.retweets match {
      case _: Int => {
        if(leftMost.retweets > elem.retweets) {
          rightMost.retweets match {
            case _: Int => {
              if(rightMost.retweets > leftMost.retweets) rightMost
              else leftMost
            }
            case _ => leftMost
          }
        }
      }
      case _ => {
        rightMost.retweets match {
          case _: Int => {
            if(rightMost.retweets > elem.retweets) rightMost
            else elem
          }
          case _ => elem
        }
      }  
    }
    */
    var most: Tweet = elem
    left.union(right).foreach(tweet => if (tweet.retweets > most.retweets) most = tweet)
    most 
	 
  }

  def descendingByRetweet: TweetList = {
    new Cons(mostRetweeted, this.remove(mostRetweeted).descendingByRetweet)
  }


  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
}


/** Trait que representa uma lista simplesmente encadeada de tweets.
  */
trait TweetList {
  def head: Tweet
  def tail: TweetList
  def isEmpty: Boolean
  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")
  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")
  def isEmpty = true
}

case class Cons(head: Tweet, tail: TweetList) extends TweetList {
  def isEmpty = false
}


/** Objeto que encapsula os dados referentes aos tweets do Google e da
  * Apple.
  */
object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  lazy val googleTweets: TweetSet = TweetReader.allTweets.filter(tweet => google.exists(word => tweet.text contains word))
  lazy val appleTweets: TweetSet = TweetReader.allTweets.filter(tweet => apple.exists(word => tweet.text contains word))


  /** Uma lista de todos os tweets mencionando uma palavra chave de
    * qualquer das duas listas, apple ou google, ordenada pelo número
    * de retweets.
    */
  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet
}


object Main extends App {
  // Imprime os “trending tweets” (tweets populares?)
  GoogleVsApple.trending foreach println
}
