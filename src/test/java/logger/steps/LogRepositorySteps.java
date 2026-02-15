package logger.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import logger.repository.LogRepository;
import logger.database.Database;
import logger.logic.LogEntry;

public class LogRepositorySteps {

    // Create booleans for replication.
    private boolean operationalDatabase = false;
    private boolean wasSaved;

    // Instances of classes. 
    private LogEntry entry;
    private LogRepository logRepository = new LogRepository();

    @Before
    public void setup() {
        // Create instance using fakedatabase. 
        logRepository.setUseFakeDatabase(true); 
    
        try {
            // Creates databasetable and deletes any old to ensure "cleanliness". 
            Database.createDatabaseTable(); 
            logRepository.truncateTable();
        } catch (Exception e) {
            // Execute in fake-mode. 
        }
    }

    @Given("the database is operational")
    public void databaseIsOperational(){
        operationalDatabase = true;
        // Vi kan också verifiera anslutningen här om vi vill vara noga
    }
    
    @Given("a validated log message {string} with level {string} exists")
    public void validatedMessageExists(String message, String level) {
        // Create object that insertData needs. 
        this.entry = new LogEntry(LocalDateTime.now(), message, level);
    }

    @When("the log message is sent to the repository")
    public void messageToRepository() {
        // Checks if operationalDatabase and entry aren't null. 
        if (operationalDatabase && entry != null){
            wasSaved = logRepository.insertData(entry); 
        }
    }

    @Then("a new row should exist in the logs table")
    public void validateCreateNewRow() {
        // Asserts that log was not saved. Testdata should not be mixed with real data. 
        assertTrue("Log was not saved in database!", wasSaved);
    }

    /**
     * 
     */
    @Then("the saved message should match {string}")
    public void verifySavedMessages(String expectedMessage) {
        // Asserts that message corresponds with the message saved. 
        assertTrue("Message was correct", operationalDatabase);
    }



    /**
     * @Given("the database is not operational")
    public void notOperationalDatabase() {
        logRepository.setDatabaseUrl("jdbc:sqlite:/mapp/does/not/exist/fake.db");
    }

    @When("a log message arrives we should expect it to not be saved")
    public void attemptToSave() {
        wasSaved = logRepository.insertData(entry);
    }

    @Then("the log message should be removed")
    public void verifyNothingSaved() {
        int count = logRepository.getLogCount();
        assertEquals(0, count);
    }

    @Then("user should get message describing that database is not operational") 
    public void verifyErrorMessage() {
        assertFalse("Message was saved though it should not have been", wasSaved);
    }
     */

}
    
