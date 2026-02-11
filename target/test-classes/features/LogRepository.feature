Feature: Database validation logic

    Background: 
        Log message is validated and accepted

    Scenario Outline: Message is succesfully saved in database
    When a log message arrives we should expect it to be validated and ready for insertion in to database.
    Then log message should be sent in to matching rows either timestamp, message or type. 
    And user should get a confirmation message

    Scenario Outline: Message is correctly disregarded if database shuts down
    When a 