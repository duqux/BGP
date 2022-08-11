package Stepdefinition;

import Modules.Commons;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonStepDefinition {
    Commons commons = new Commons();

    @Given("^I navigate to portal$")
        public void i_navigate_to_portal(){
        commons.navigate();
        }

    @And("^I login to portal$")
    public void i_login_to_portal() {
        commons.login();
    }

    @Then("^I create an application form$")
    public void I_create_an_application_form() throws InterruptedException {
        commons.createNewGrant();
    }

    @And("^I click on \\\"(.*)\\\" section tab$")
    public void i_click_on_section_tab(String section) {
        commons.clickOnSection(section);
    }

    @Then("^I click on logout button$")
    public void i_click_on_logout_button(){
        commons.logout();
    }

    @And("^I click on next button$")
    public void i_click_on_next_button() throws InterruptedException {
        commons.next();
    }

    @Then("^I delete drafts$")
    public void i_delete_drafts() throws InterruptedException {
        commons.deleteDraft();
    }
}
