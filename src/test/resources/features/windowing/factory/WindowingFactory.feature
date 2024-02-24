Feature:  Using a WindowingFactory
  A Windowing Factory is a factory for the concept of windowing, a transformation between two kinds of sequences

  Scenario Outline: A Trivial Windowing should return as output what it gets as input
    When I get a trivial Windowing
    And I process <arg0>
    Then the result should contain <res>
    Examples:
      | arg0 | res |
      | "a"  | "a" |
      | "b"  | "b" |
      | "a"  | "a" |

  Scenario: A Pairing Windowing should return the last two processed values as tuples
    When I get a Pairing Windowing
    Then it should output a tuple with the last two processed values


  Scenario: A Sum Last 4 Windowing should sum the last 4 processed values and return nothing before 4 values are passed
    When I get a Sum Last four Windowing
    Then it should output the sum of the last four processed values

  Scenario: A Last N Windowing should return a list of the last N processed inputs, and return nothing the first N-1 times
    When I get a Last 4 Windowing
    Then it should output a list of the last 4 processed values


  Scenario: A Last Whose Sum Is At Least N Windowing should return the list of the last processed element whose sum is at least N
    When I get a Last Whose Sum Is At Least 10 Windowing
    Then it should return nothing as long as the sum isn't 10
    And after the total is > 10, it should return the list of number whose sum is greater than 10