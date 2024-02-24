package windowing.factory

import windowing.Windowing

trait WindowingFactory:
  def trivial[A]: Windowing[A, A]

  def pairing[A]: Windowing[A, (A, A)]

  def summing(n: Int): Windowing[Int, Int]

object WindowingFactory:
  def apply(): WindowingFactory = WindowingFactoryImpl()

  private class WindowingFactoryImpl extends WindowingFactory:
    override def trivial[A]: Windowing[A, A] = Windowing {
      case head :: tail => Some(head)
      case _ => None
    }

    override def pairing[A]: Windowing[A, (A, A)] = Windowing {
      case first :: second :: tail => Some(second, first)
      case _ => None
    }

    override def summing(n: Int): Windowing[Int, Int] = Windowing {
      case l if l.length >= 4 => Some(l.zipWithIndex.filter((_, idx) => idx < 4).map(_._1).sum)
      case _ => None
    }


