package calculator;

import io.cucumber.java.en.*;

public class CalculatorSteps {
    private Calculator calculator;

    @Given("I have a Calculator")
    public void iHaveACalculator() {
        this.calculator = new Calculator();
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        if (arg0 != this.calculator.getResult()) {
            throw new IllegalStateException();
        }
    }

    @When("I enter {int} and {int}")
    public void iEnterAnd(Integer int1, Integer int2) {
        this.calculator.enter(int1);
        this.calculator.enter(int2);
    }

    @And("I press the subtraction operator")
    public void iPressTheSubtractionOperator() {
        this.calculator.subtract();
    }


    @And("I press the addition operator")
    public void iPressTheAdditionOperator() {
        this.calculator.add();
    }

    @And("I press the multiplication operator")
    public void iPressTheMultiplicationOperator() {
        this.calculator.multiply();
    }
}
