Feature: Registration functionality for BuggyCars

  Scenario: 1. Verify user can successfully register and subsequently log in to the system.
    Given Navigate to registration page
    Then User can with register with all valid details
    And User can login with registered details
    And User is logged in successfully

  Scenario: 2. Verify users cannot complete the registration process unless all mandatory fields are filled out.
    Given Navigate to registration page
    When User enters the below registration data and clicks login
      | login | firstname | lastname | password | confirmPassword |
      |       | fff       | dshsdv   | jksdsd   | ssj             |
      | test  |           | dd       | jksdsd   | dssdsd          |
      | test  | nndk      |          | jksdsd   | jksdsd          |
      | test  | nndk      | dddd     |          | jksdsd          |
      | test  | nndk      | dddd     | dsds     |                 |


