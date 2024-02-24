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

  var sumWindowing: Windowing[Int, Int] = null
  When("""I get a Sum Last four Windowing"""):
    () => sumWindowing = WindowingFactory().summing(4)

  Then("""it should output the sum of the last four processed values"""):
    () =>
      sumWindowing.process(1) match
        case None => // Right path
        case _ => throw IllegalStateException()
      sumWindowing.process(10) match
        case None => // Right path
        case _ => throw IllegalStateException()
      sumWindowing.process(100) match
        case None => //Right path
        case _ => throw IllegalStateException()
      sumWindowing.process(1000) match
        case Some(1111) => // Right path
        case _ => throw IllegalStateException()
      sumWindowing.process(2) match
        case Some(1112) => // Right path
        case _ => throw IllegalStateException()
      sumWindowing.process(20) match
        case Some(1122) => // Right path
        case _ => throw IllegalStateException()

  var last4Windowing: Windowing[Int, List[Int]] = null
  When("""I get a Last four Windowing"""):
    () => last4Windowing = WindowingFactory().last(4)

  Then("""it should output a list of the last four processed values"""):
    () =>
      last4Windowing.process(1) match
        case None => // Right path
        case _ => throw IllegalStateException()
      last4Windowing.process(10) match
        case None => // Right path
        case _ => throw IllegalStateException()
      last4Windowing.process(100) match
        case None => //Right path
        case _ => throw IllegalStateException()
      last4Windowing.process(1000) match
        case Some(List(1, 10, 100, 1000)) => // Right path
        case _ => throw IllegalStateException()
      last4Windowing.process(2) match
        case Some(List(10, 100, 1000, 2)) => // Right path
        case _ => throw IllegalStateException()
      last4Windowing.process(20) match
        case Some(List(100, 1000, 2, 20)) => // Right path
        case _ => throw IllegalStateException()

  var sumAtLeastWindowing: Windowing[Int, List[Int]] = null
  When("""I get a Last Whose Sum Is At Least ten Windowing"""):
    () => sumAtLeastWindowing = WindowingFactory().sumAtLeast(10)

  Then("""it should return nothing as long as the sum isn't ten"""):
    () =>
      sumAtLeastWindowing.process(5) match
        case None => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(3) match
        case None => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(1) match
        case None => // Right path
        case _ => throw IllegalStateException()

  And("""after the total is > ten, it should return the list of number whose sum is greater than ten"""):
    () =>
      sumAtLeastWindowing.process(1) match
        case Some(List(5, 3, 1, 1)) => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(2) match
        case Some(List(5, 3, 1, 1, 2)) => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(4) match
        case Some(List(3, 1, 1, 2, 4)) => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(8) match
        case Some(List(4, 8)) => // Right path
        case _ => throw IllegalStateException()
      sumAtLeastWindowing.process(20) match
        case Some(List(20)) => // Right path
        case _ => throw IllegalStateException()
