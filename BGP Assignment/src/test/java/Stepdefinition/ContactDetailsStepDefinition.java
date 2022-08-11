package Stepdefinition;

import Modules.Commons;
import Modules.ContactDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ContactDetailsStepDefinition {

    ContactDetails contactDetails = new ContactDetails();

    @Then("^I check contact details fields$")
    public void i_check_contact_details_fields() {
        contactDetails.fieldCheck();
    }

    @Then("^I auto populate address$")
    public void i_auto_populate_address() throws InterruptedException {
    contactDetails.autoPopulateAdd();
    }

    @And("^I check auto populate address$")
    public void i_check_auto_populate_address() throws InterruptedException {
        contactDetails.checkAutoPopulateAdd();
    }

    @And("^I click on same as registered check box$")
    public void i_click_on_same_as_register_check_box() {
        contactDetails.clickSameAdd();
    }

    @Then("^I check on the populated fields$")
    public void i_check_on_the_populated_fields() throws InterruptedException {
        contactDetails.checkSameAdd();
    }

    @Then("^I fill in main contact details$")
    public void i_fill_main_contact_details() {
        contactDetails.fillInPersonDetails();
    }

    @And("^I check on the main contact details$")
    public void i_check_on_the_main_contact_details(){
        contactDetails.checkPersonDetails();
    }

    @And("^I click on same as main person check box$")
    public void i_click_on_same_as_main_person_check_box() {
        contactDetails.clickSameMainContact();
    }

    @Then("^I check on the letter of offer populated fields$")
    public void i_check_on_the_letter_of_offer_populated_fields() throws InterruptedException {
        contactDetails.checkSameMainContact();
    }

    @Then("^I check on addressee error message$")
    public void i_check_on_addressee_error_message() throws InterruptedException {
        contactDetails.verifyErrorMessage();
    }

    @Then("^I fill in letter of addressee$")
    public void i_fill_in_letter_of_addressee() {
        contactDetails.fillInLetterOfAddressee();
    }

    @Then("^I fill in incorrect person details$")
    public void i_fill_in_incorrect_person_details(){
        contactDetails.fillInIncorrectPersonDetails();
    }

    @Then("^I fill in incorrect LOOA details$")
    public void i_fill_in_incorrect_looa_details() throws InterruptedException {
        contactDetails.fillInIncorrectLOOA();
    }

    @Then("^I check mandatory for Contact Details$")
    public void i_check_mandatory_for_contact_details() throws InterruptedException {
        contactDetails.checkMandatory();
    }
}
