package windowing.factory

import io.cucumber.scala.{EN, PendingException, ScalaDsl}
import windowing.Windowing


class WindowingFactorySteps extends ScalaDsl with EN:
  var stringWindowing: Windowing[String, String] = null
  var stringRes: Option[String] = Option.empty
  When("""I get a trivial Windowing"""):
    () => stringWindowing = WindowingFactory().trivial

  And("""I process {string}"""):
    (arg0: String) => stringRes = stringWindowing.process(arg0)

  Then("""the result should contain {string}"""):
    (res: String) => if stringRes.get != res then throw IllegalStateException()

