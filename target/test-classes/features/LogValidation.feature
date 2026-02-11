Feature: Log Validation logic

    Background:
        Given log message is received by log validation

    Scenario Outline: Successful validation
        When a log message is created with time "<time_input>", message "<msg_input>", and level "<type>"
        Then the log message should be accepted 
        And the user should get a confirmation message

    Examples: 
        | time_input          | msg_input      | type     |
        | 2026-02-11T11:00:00 | System start   | INFO     |
        | 2026-02-11T11:00:00 | System start   | WARNING  |

    Scenario Outline: Failed validation due to missing data
        When a log message is created with time "<time_input>", message "<msg_input>", and level "<type>"
        Then the log message should be rejected due to missing data
        And the user should get a rejection message describing the error

    Examples: 
        | time_input | msg_input      | type  |
        |            | System start   | DEBUG |
        | 2026-02-11 |                | ERROR |