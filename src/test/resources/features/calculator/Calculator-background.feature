Feature:  Adding numbers with a Calculator
  In order to not learn math
  As someone who is bad at math
  I want to be able to add numbers using a Calculator

  Background: Start with a Calculator
    Given I have a Calculator
  Scenario:  Add two positive numbers
    When I enter 1 and 1
    And I press the addition operator
    Then the result should be 2

  Scenario:  Add a positive and negative number
    When I enter 1 and -1
    And I press the addition operator
    Then the result should be 0

  Scenario:  Add two negative numbers
    When I enter -1 and -1
    And I press the addition operator
    Then the result should be -2
