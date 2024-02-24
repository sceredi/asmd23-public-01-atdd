package windowing

trait Windowing[A, B]:
  val values: Seq[A]

  def process(value: A): Option[B]
