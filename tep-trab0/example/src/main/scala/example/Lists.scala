package example

object Lists {
  /**
    * Este metodo computa a soma de todos os elementos na lista
    * xs. Existem varias tecnicas que podem ser utilizadas para
    * implementar este metodo, e voce vai aprender algumas delas
    * durante a disciplina.
    *
    * Para este projeto de programacao de exemplo, voce pode utilizar
    * os seguintes métodos da classe 'List':
    * - 'xs.isEmpty: Boolean' retorna 'true' se a lista 'xs' for vazia;
    * - 'xs.head: Int' retorna a cabeça (primeiro elemento) da lsita
    *   'xs'. Se a lista for fazia é disparada uma exceção.
    * - 'xs.tail: List[Int]' retorna a calda da lista 'xs', i.e., a
    *   lista 'xs' sem o elemento da cabeca.
    *
    * 'Dica:' Ao inves de tentar escrever um 'for' ou 'while', pense
    * em uma solucao recursiva.
    *
    * @param xs Uma lista de numero inteiros
    * @return A soma de todos os elementos em 'xs'
    */
  def sum(xs: List[Int]): Int = {
    if(xs.isEmpty) 0 else xs.head + sum(xs.tail)
  }

  /**
    * Este metodo retorna o maior elemento em uma lista de
    * inteiros. Se a lista 'xs' for vazia, eh disparada uma excecao
    * 'fava.util.NoSuchElementException'.
    *
    * Voce pode utilizar os mesmos metodos da classe 'List'
    * mencionados acima.
    *
    * 'Dica': Novamente, pense em uma solucao recurciva ao inves de
    * utilizar laços. Pode ser que voce precise definir uma funcao
    * auxiliar.
    *
    * @param xs Uma lista de numeros inteiros
    * @return O maior elemento em 'xs'
    * @throws java.util.NoSuchElementException Se 'xs' for uma lista vazia
    */
  def max(xs: List[Int]): Int = {
    if(xs.isEmpty) throw new NoSuchElementException("Lista vazia")
    def maior(l: List[Int], valor: Int): Int = {
      if(l.isEmpty){
        valor
      }
      else{
        if(valor > l.head) maior(l.tail, valor) else maior(l.tail, l.head)
      }
    }
    maior(xs.tail, xs.head)
  }
}
