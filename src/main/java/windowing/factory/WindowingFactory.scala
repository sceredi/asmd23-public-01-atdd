package windowing.factory

import windowing.Windowing

trait WindowingFactory:
  def trivial[A, B]: Windowing[A, B]


