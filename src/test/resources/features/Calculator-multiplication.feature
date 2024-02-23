Feature: Multiplying two numbers with a Calculator
  As a non math learner I also don't want to learn Multiplication
  I still want to be able to multiply two numbers

  Background: Start with a Calculator
    Given I have a Calculator

  Scenario Outline: Multiply any two numbers
    When I enter <arg0> and <arg1>
    And I press the multiplication operator
    Then the result should be <res>
    Examples:
      | arg0 | arg1 | res |
      | 2    | 1    | 2   |
      | 2    | 0    | 0   |
      | 2    | -1   | -2  |
      | -2   | -2   | 4   |
