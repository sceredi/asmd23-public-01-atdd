package windowing.factory

import windowing.Windowing

trait WindowingFactory:
  def trivial[A]: Windowing[A, A]

  def pairing[A]: Windowing[A, (A, A)]

  def summing(n: Int): Windowing[Int, Int]

  def last[A](n: Int): Windowing[A, List[A]]

  def sumAtLeast(n: Int): Windowing[Int, List[Int]]

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

    override def last[A](n: Int): Windowing[A, List[A]] = Windowing {
      case l if l.length >= n => Some(l.zipWithIndex.filter(_._2 < n).map(_._1).toList.reverse)
      case _ => None
    }

    override def sumAtLeast(n: Int): Windowing[Int, List[Int]] = Windowing {
      case l if l.sum < n => None
      case l => Some(sumTo(l.toList, n).reverse)
    }

    private def sumTo(list: List[Int], n: Int): List[Int] = list match
      case head :: tail if head >= n => List(head)
      case head :: tail => head :: sumTo(tail, n - head)
      case _ => List.empty


