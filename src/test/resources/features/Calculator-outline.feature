Feature:  Adding numbers with a Calculator
  In order to not learn math
  As someone who is bad at math
  I want to be able to add numbers using a Calculator

  Scenario Outline: Add two numbers
    Given I have a Calculator
    When I enter <arg0> and <arg1>
    And I press the addition operator
    Then the result should be <res>
    Examples:
      | arg0 | arg1 | res |
      | 1    | 1    | 2   |
      | 1    | -1   | 0   |
      | -5   | -6   | -11 |