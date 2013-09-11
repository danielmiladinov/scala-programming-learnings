package net.miladinov.typeParameterization

object BasicQueue {
  def apply[T](elements: T*) = new BasicQueue(elements.toList, Nil)
}

class BasicQueue[T] private (
  private val leading: List[T],
  private val trailing: List[T]
) extends FunctionalQueue[T] {

  private def mirror =
    if (leading.isEmpty)
      new BasicQueue(trailing.reverse, Nil)
    else
      this

  def head: T = mirror.leading.head

  def tail: FunctionalQueue[T] = {
    val q = mirror
    new BasicQueue(q.leading.tail, q.trailing)
  }

  def enqueue(element: T): FunctionalQueue[T] = new BasicQueue(leading, element :: trailing)
}