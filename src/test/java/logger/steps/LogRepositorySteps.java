package logger.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import logger.repository.LogRepository;
import logger.logic.LogEntry;

public class LogRepositorySteps {

    private boolean validatedMessage;
    private boolean operationalDatabase;
    private String sql;
    
    LogEntry entry;
    LogRepository logRepository;


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
        logRepository = new LogRepository();
    }
    
    @Given("a validated log message {System start} with level {INFO} exists")
    public void validatedMessageExists(String timestamp, String message, String level) {
        if (operationalDatabase) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
            entry = new LogEntry(dateTime, message, level);
        }
    }

    @When("the log message is sent to the repository")
    public void messageToRepository() {
        try {
            logRepository.insertData(entry);
            validatedMessage = true;
        } catch (Exception e) {
            validatedMessage = false;
        }
    }

    @Then("a new row should exist in the {logs} table")
    public void validateCreateNewRow() {
        
    }

    @Then("the saved message should match {System start}")
    public void systemStartMessage() {
        assertTrue(validatedMessage);
    }
}
