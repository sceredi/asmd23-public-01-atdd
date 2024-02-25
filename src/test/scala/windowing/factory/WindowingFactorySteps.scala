package windowing.factory

import io.cucumber.scala.{EN, ScalaDsl}
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
      val testCases = List(
        (1, None),
        (3, Some(1, 3)),
        (2, Some(3, 2)),
        (1, Some(2, 1)),
      )
      testCases.foreach((value, expectedRes) => assert(pairingWindowing.process(value) == expectedRes))

  var sumWindowing: Windowing[Int, Int] = null
  When("""I get a Sum Last four Windowing"""):
    () => sumWindowing = WindowingFactory().summing(4)

  Then("""it should output the sum of the last four processed values"""):
    () =>
      val testCases = List(
        (1, None),
        (10, None),
        (100, None),
        (1000, Some(1111)),
        (2, Some(1112)),
        (20, Some(1122)),
      )
      testCases.foreach((value, expectedRes) => assert(sumWindowing.process(value) == expectedRes))


  var last4Windowing: Windowing[Int, List[Int]] = null
  When("""I get a Last four Windowing"""):
    () => last4Windowing = WindowingFactory().last(4)

  Then("""it should output a list of the last four processed values"""):
    () =>
      val testCases = List(
        (1, None),
        (10, None),
        (100, None),
        (1000, Some(List(1, 10, 100, 1000))),
        (2, Some(List(10, 100, 1000, 2))),
        (20, Some(List(100, 1000, 2, 20))),
      )
      testCases.foreach((value, expectedRes) => assert(last4Windowing.process(value) == expectedRes))

  var sumAtLeastWindowing: Windowing[Int, List[Int]] = null
  When("""I get a Last Whose Sum Is At Least ten Windowing"""):
    () => sumAtLeastWindowing = WindowingFactory().sumAtLeast(10)

  Then("""it should return nothing as long as the sum isn't ten"""):
    () =>
      val testCases = List(
        (5, None),
        (3, None),
        (1, None),
      )
      testCases.foreach((value, expectedRes) => assert(sumAtLeastWindowing.process(value) == expectedRes))

  And("""after the total is > ten, it should return the list of number whose sum is greater than ten"""):
    () =>
      val testCases = List(
        (1, Some(List(5, 3, 1, 1))),
        (2, Some(List(5, 3, 1, 1, 2))),
        (4, Some(List(3, 1, 1, 2, 4))),
        (8, Some(List(4, 8))),
        (20, Some(List(20))),
      )
      testCases.foreach((value, expectedRes) => assert(sumAtLeastWindowing.process(value) == expectedRes))
