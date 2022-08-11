package Stepdefinition;

import Modules.Commons;
import Modules.EligibilitySection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class EligibilitySectionStepDefinition {
    EligibilitySection eligibilitySection = new EligibilitySection();

    @And("^I check the accuracy of the questions$")
    public void i_check_the_accuracy_of_the_questions() {
        eligibilitySection.checkQuestions();
    }

    @And("^I check for radio buttons$")
    public void i_check_for_radio_buttons() {
        eligibilitySection.checkRadioButtons();
    }

    @Then("^I click on \\\"(.*)\\\" radio button for \\\"(.*)\\\"")
    public void i_click_on_radio_button_for(String option, String path){
        eligibilitySection.radiobuttonClick(option,path);
    }

    @Then("^I click on \\\"(.*)\\\" for all radio buttons")
    public void i_click_on_radio_button_for_all_radio_buttons(String option){
        eligibilitySection.radiobuttonAllClick(option);
    }

    @And("^I check for error message$")
    public void i_check_for_error_message(){
        eligibilitySection.checkErrorMessage();
    }

    @And("^I save draft$")
    public void i_save_draft(){
        eligibilitySection.saveDraft();
    }
    @Then("^I check for saved draft for \\\"(.*)\\\" radio button$")
    public void i_check_for_saved_draft(String option){
        eligibilitySection.checkDraft(option);
    }

    @And("^I check for Mandatory$")
    public void i_check_for_Mandatory(){
        eligibilitySection.checkMandatory();
    }
}

