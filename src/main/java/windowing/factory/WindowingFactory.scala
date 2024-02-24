package windowing.factory

trait Windowing[A, B]:
  def process(value: A): Option[B]

trait WindowingFactory:
  def trivial[A, B]: Windowing[A, B]


