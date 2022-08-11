package Stepdefinition;

import Modules.Commons;
import Modules.Proposal;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ProposalStepDefinition {
    Proposal proposal = new Proposal();

    @Then("^I fill in proposal section$")
    public void i_fill_in_proposal_section() {
        proposal.fillInProposal();
    }
}
