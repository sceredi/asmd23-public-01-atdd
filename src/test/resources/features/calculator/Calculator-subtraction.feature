Feature: Subtracting two numbers with a Calculator
  As a non math learner I also don't want to learn subtraction
  I still want to be able to subtract two numbers

  Background: Start with a Calculator
    Given I have a Calculator

  Scenario Outline: Subtract any two numbers
    When I enter <arg0> and <arg1>
    And I press the subtraction operator
    Then the result should be <res>
    Examples:
      | arg0 | arg1 | res |
      | 1    | 1    | 0   |
      | -1   | 1    | -2  |
      | 1    | -1   | 2   |
      | -1   | -1   | 0   |
