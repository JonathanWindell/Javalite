package logger.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import java.time.LocalDateTime;


public class LogValidationSteps {

    public boolean messageReceived;
    public LocalDateTime testTimeStamp;
    public String testMessage;
    public String testLevel;

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
            testTimeStamp = LocalDateTime.now();
            testMessage = "This is valid formatting";
            testLevel = "Warning";

            LogEntry logEntry = new LogEntry(timestamp, message, level);
        }
        
    }

    @Then("the log message should be accepted") 
    public void verify_acceptance() {

    }

    
    

}
