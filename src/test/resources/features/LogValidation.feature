Feature: Log Validation logic

    Background:
        Given log message is received by log validation

    Scenario Outline: Successful validation
        When a log message is created with time "2007-12-03T10:15:30", message "System start", and level "<type>"
        Then the log message should be accepted 
        And the user should get a confirmation message

    Scenario Outline: Failed validation due to missing data
        When log message is created with missing data time "", message "System shutdown" and level "<type>"
        Then the log message should be rejected due to missing data
        And the user should get a rejection message describing the error

    Scenario Outline: Failed validation due to invalid data
        When log message is created with invalid data time "Hello", message "System go crazy" and level "<type>"
        Then the log message should be rejected due to invalid data
        And the user should get a rejection message describing the error

    Examples: 
        | type |
        | DEBUG |
        | INFO |
        | WARNING |
        | ERROR |
        | CRITICAL |    