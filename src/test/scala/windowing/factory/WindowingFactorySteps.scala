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


  var pairingWindowing: Windowing[Int, (Int, Int)] = null
  var pairingRes: Option[(Int, Int)] = Option.empty

  When("""I get a Pairing Windowing"""):
    () => pairingWindowing = WindowingFactory().pairing

  Then("""it should output a tuple with the last two processed values"""):
    () =>
      pairingWindowing.process(1) match
        case None => // Right path
        case _ => throw IllegalStateException()
      pairingWindowing.process(3) match
        case Some(1, 3) => // Right path
        case _ => throw IllegalStateException()
      pairingWindowing.process(2) match
        case Some(3, 2) => // Right path
        case _ => throw IllegalStateException()
      pairingWindowing.process(1) match
        case Some(2, 1) => // Right path
        case _ => throw IllegalStateException()