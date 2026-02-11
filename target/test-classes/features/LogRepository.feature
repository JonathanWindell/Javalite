Feature: Database validation logic

    Background: 
        Log message is validated and accepted

    Scenario Outline: Message is succesfully saved in database
    Given the database is operational
    And a validated log message "System start" with level "INFO" exists
    When the log message is sent to the repository
    Then a new row should exist in the "logs" table
    And the saved message should match "System start"

    Scenario Outline: Message is disregarded if database is not operational
    When a log message arrives we should expect it to not be saved
    Then the log message should be removed 
    And user should get message describing that database is not operational