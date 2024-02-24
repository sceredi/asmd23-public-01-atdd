package windowing.factory

import windowing.Windowing

trait WindowingFactory:
  def trivial[A]: Windowing[A, A]

object WindowingFactory:
  def apply(): WindowingFactory = WindowingFactoryImpl()

  private class WindowingFactoryImpl extends WindowingFactory:
    override def trivial[A]: Windowing[A, A] = Windowing(_.lastOption)

