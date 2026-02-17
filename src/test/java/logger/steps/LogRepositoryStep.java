package logger.steps;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import logger.database.Database;
import logger.logic.LogEntry;
import logger.repository.LogRepository;

public class LogRepositoryStep {

  // Create booleans for replication.
  private boolean operationalDatabase = false;
  private boolean wasSaved;

  // Instances of classes.
  private LogEntry entry;
  private LogRepository logRepository = new LogRepository();

  @Before
  public void setup() {
    logRepository.setUseFakeDatabase(false);

    // Using dedicated test file instead of my.db
    String testUrl = "jdbc:sqlite:test_logger.db";
    logRepository.setCorrectUrl(testUrl);

    try {
      // Create table in test-database
      Database.createDatabaseTable(testUrl);
      logRepository.truncateTable();
    } catch (Exception e) {
      // Fall back to fake-mode
      logRepository.setUseFakeDatabase(true);
      System.err.println("Could not initalize fake-db. Entering fake mode: " + e.getMessage());
    }
  }

  @Given("the database is operational")
  public void databaseIsOperational() {
    operationalDatabase = true;
  }

  @Given("a validated log message {string} with level {string} exists")
  public void validatedMessageExists(String message, String level) {
    // Create object that insertData needs.
    this.entry = new LogEntry(LocalDateTime.now(), message, level);
  }

  @When("the log message is sent to the repository")
  public void messageToRepository() {
    // Checks if operationalDatabase and entry aren't null.
    if (operationalDatabase && entry != null) {
      wasSaved = logRepository.insertData(entry);
    }
  }

  @Then("a new row should exist in the logs table")
  public void validateCreateNewRow() {
    // Asserts that log was not saved. Testdata should not be mixed with real data.
    assertTrue("Log was not saved in database!", wasSaved);
  }

  @Then("the saved message should match {string}")
  public void verifySavedMessages(String expectedMessage) {
    // Asserts that message corresponds with the message saved.
    assertTrue("Message was correct", operationalDatabase);
  }

  /** Error scenario. Error path */
  @Given("the database is not operational")
  public void notOperationalDatabase() {
    logRepository.setUseFakeDatabase(false);

    logRepository.setCorrectUrl("jdbc:sqlite:/mapp/som/inte/finns/error.db");
  }

  @When("a log message arrives we should expect it to not be saved")
  public void attemptToSave() {
    wasSaved = logRepository.insertData(entry);
  }

  @Then("the log message should be denied")
  public void verifyNothingSaved() {
    int count = logRepository.getLogCount();
    assertEquals(0, count);
  }

  @Then("user should get message describing that database is not operational")
  public void verifyErrorMessage() {
    assertFalse("Message was saved though it should not have been", wasSaved);
  }

  @After
  public void tearDown() {
    logRepository.setCorrectUrl("jdbc:sqlite:my.db");
    logRepository.setUseFakeDatabase(false);

    operationalDatabase = false;
    wasSaved = false;

    System.out.println(">>> Cleaning done. DatabaseUrl is reverted.");
  }
}
