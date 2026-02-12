package logger.steps;

import logger.logic.LogValidation;
import logger.logic.LogEntry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class LogValidationSteps {

    public LocalDateTime testTimeStamp; 
    public String testMessage;
    public String testLevel;

    public boolean messageReceived = false;
    private boolean validationResult;
    private LogValidation validator = new LogValidation();

    /**
     * Step needed for execution of testcases. 
     */
    @Given("log message is received by log validation")
    public void log_message_is_received_by_log_validation() {
        messageReceived = true;
    }

    /**
     * Tests happy path to validate that correctly formatted log is forwarded to repository. 
     * 
     * @param timestamp
     * @param message
     * @param level
     */
    @When("a log message is created with time {string}, message {string}, and level {string}")
    public void log_message_is_correctly_formatted(String timestamp, String message, String level) {
        if (messageReceived) {
        LocalDateTime timeObj = null;
            try {
                if (!timestamp.isEmpty()) {
                    timeObj = LocalDateTime.parse(timestamp);
                }
            } catch (Exception e) {
                // timeObj stay null. 
            }


        LogEntry entry = new LogEntry(timeObj, message, level);
        this.validationResult = validator.isValid(entry);
        }
        
    }

    @Then("the log message should be accepted") 
    public void verify_acceptance() {
        assertTrue("Log should be accepted", this.validationResult);
    }

    @Then("the log message should be rejected due to missing data")
    public void deny_acceptance() {
        assertFalse("Log should be denied",  this.validationResult);
    }

    @Then("the user should get a confirmation message")
    public void verify_acceptance_confirmation() {
        assertTrue(validationResult);
    }

    @Then("the user should get a rejection message describing the error")
    public void send_rejection_confirmation( ){
        assertFalse(validationResult);
    }

}
