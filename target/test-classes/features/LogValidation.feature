Feature: Log Validation logic

    Background:
        Given log message is received by log validation

    Scenario Outline: Successful validation
        When a log message is created with time "2023-10-27-10-00", message "System start", and level "<type>"
        Then the log message should be accepted 
        And the user should get a confirmation message

    Scenario Outline: Failed validation due to invalid data
        When log message is created with invalid or missing data
        Then the log message should be rejected 
        And the user should get a rejection message describing the error

    Examples: 
        | type |
        | DEBUG |
        | INFO |
        | WARNING |
        | ERROR |
        | CRITICAL |    