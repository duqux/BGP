package Stepdefinition;


import Modules.Cost;
import io.cucumber.java.en.Then;

import java.awt.*;

public class CostStepDefinition {
   Cost cost = new Cost();

    @Then("^I fill in cost section$")
    public void i_fill_in_cost_section() throws InterruptedException, AWTException {
        cost.fillInCost();
    }
}
