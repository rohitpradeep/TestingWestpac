Feature: Rating functionality

  Scenario: 1. Verify that the user is able to vote and add comments on  car model.
    Given Navigate to registration page
    Then User can with register with all valid details
    And User can login with registered details
    And User is logged in successfully
    Then Navigate to overall rating page
    Then User is able to vote on model below and place comments
      | Model     | Comments     |
      | Mito      | testComments |
      | Diablo    | testComments |
      | 4c Spider | testComments |




