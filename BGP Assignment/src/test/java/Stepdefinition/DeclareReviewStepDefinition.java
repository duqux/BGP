package Stepdefinition;

import Modules.DeclareReview;
import Modules.Proposal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class DeclareReviewStepDefinition {
    DeclareReview declareReview = new DeclareReview();

    @Then("^I fill in Declare section$")
    public void i_fill_in_proposal_section(){
        declareReview.fillInDeclareReview();

    }
    @And("^I click on consent check box$")
    public void i_click_on_consent_check_box() throws InterruptedException {
        declareReview.consentCheck();
    }

    @And("^I click on review button$")
    public void i_click_on_review_button() throws InterruptedException {
        declareReview.clickReviewButton();
    }

    @Then("^I check on review page$")
    public void i_check_on_review_page() {
        declareReview.checkReviewPage();
    }

    @Then("^I submit for review$")
    public void i_submit_for_review() throws InterruptedException {
        declareReview.submitForReview();
    }

    @Then("^I check mandatory for consent$")
    public void i_check_mandatory_for_consent() throws InterruptedException {
        declareReview.checkMandatory();
    }
}
