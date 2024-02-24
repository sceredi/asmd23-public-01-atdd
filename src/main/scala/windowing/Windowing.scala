package windowing

trait Windowing[A, B]:
  val values: Seq[A]

  def process(value: A): Option[B]

object Windowing:
  def apply[A, B](f: Seq[A] => Option[B]): Windowing[A, B] = new WindowingImpl[A, B](f)

  private class WindowingImpl[A, B](f: Seq[A] => Option[B]) extends Windowing[A, B]:
    override val values: Seq[A] = List.empty

    override def process(value: A): Option[B] = f(values :+ value)