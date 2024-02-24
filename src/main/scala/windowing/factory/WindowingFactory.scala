package windowing.factory

import windowing.Windowing

trait WindowingFactory:
  def trivial[A]: Windowing[A, A]

  def pairing[A]: Windowing[A, (A, A)]

object WindowingFactory:
  def apply(): WindowingFactory = WindowingFactoryImpl()

  private class WindowingFactoryImpl extends WindowingFactory:
    override def trivial[A]: Windowing[A, A] = Windowing{
      case head :: tail => Some(head)
      case _ => Option.empty
    }

    override def pairing[A]: Windowing[A, (A, A)] = Windowing {
      case first :: second :: tail => Some(second, first)
      case _ => Option.empty
    }

