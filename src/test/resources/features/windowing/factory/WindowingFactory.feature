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

  Scenario Outline: A Pairing Windowing should return the last two processed values as tuples
    When I get a Pairing Windowing
    And I process <arg0>
    Then the result should be <res0> <res1>
    Examples:
      | arg0 | res0 | res1 |
      | 1    |      |      |
      | 3    | 1    | 3    |
      | 2    | 3    | 2    |
      | 1    | 2    | 1    |


  Scenario Outline: A Sum Last 4 Windowing should sum the last 4 processed values and return nothing before 4 values are passed
    When I get a Sum Last 4 Windowing
    And I process <arg0>
    Then the result should be <res>
    Examples:
      | arg0 | res  |
      | 1    |      |
      | 10   |      |
      | 100  |      |
      | 1000 | 1111 |
      | 2    | 1112 |
      | 20   | 1122 |

  Scenario Outline: A Last N Windowing should return a list of the last N processed inputs, and return nothing the first N-1 times
    When I get a Last 4 Windowing
    And I process <arg0>
    Then the result should be <res0> <res1> <res2> <res3>
    Examples:
      | arg0 | res0 | res1 | res2 | res3 |
      | 1    |      |      |      |      |
      | 10   |      |      |      |      |
      | 100  |      |      |      |      |
      | 1000 | 1    | 10   | 100  | 1000 |
      | 2    | 10   | 100  | 1000 | 2    |
      | 20   | 100  | 1000 | 2    | 20   |


  Scenario Outline: A Last Whose Sum Is At Least N Windowing should return the list of the last processed element whose sum is at least N
    When I get a Last Whose Sum Is At Least 10 Windowing
    And I Process <arg0>
    Then the result should be <res>
    Examples:
      | arg0 | res       |
      | 5    |           |
      | 3    |           |
      | 1    |           |
      | 1    | 5,3,1,1   |
      | 2    | 5,3,1,1,2 |
      | 4    | 3,1,1,2,4 |
      | 8    | 4,8       |
      | 20   | 20        |