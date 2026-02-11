package logger.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import logger.repository.LogRepository;

public class LogRepositorySteps {

    private boolean validatedMessage;
    private boolean operationalDatabase;

    /**
     * Background needed for execution of every teststep. 
     */
    @Given("Log message is validated and accepted")
    public void messageIsValidated() {
        validatedMessage = true;
    }

    /**
     * Succesful scenario
     */
    @Given("Given the database is operational")
    public void databaseIsOperational(){
        operationalDatabase = true;
    }
    
    @Given("a validated log message {System start} with level {INFO} exists")
    public void validatedMessageExists() {
        // Create message 
    }

    @When("the log message is sent to the repository")
    public void messageToRepository() {
        // How?
    }

    @Then("a new row should exist in the {logs} table")
    public void validateCreateNewRow() {
        // INSERT new row
    }

    @Then("the saved message should match {System start}")
    public void systemStartMessage() {
        // assertTrue
    }
}
