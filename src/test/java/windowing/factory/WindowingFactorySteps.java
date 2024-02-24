package windowing.factory;

import io.cucumber.java.en.Given;

public class WindowingFactorySteps {
    private WindowingFactory windowingFactory;

    @Given("I have a Windowing Factory")
    public void iHaveAWindowingFactory() {
        this.windowingFactory = new WindowingFactory();
    }
}
