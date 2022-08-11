package Stepdefinition;

import Modules.BusinessImpact;
import io.cucumber.java.en.Then;

public class BusinessImpactStepDefinition {
    BusinessImpact businessImpact = new BusinessImpact();

    @Then("^I fill in business impact section$")
    public void i_fill_in_business_impact_section() {
        businessImpact.fillInBusinessImpact();
    }
}
