Feature: Login Functionality for BuggyCars

  Scenario: 1. Verify user is able to login successfully with valid login details
    Given Navigate to BuggyCars website
    When User logins with "valid" credentials
    Then User is logged in successfully

  Scenario: 2. Verify  users are unable to log in with incorrect login credentials.
    Given Navigate to BuggyCars website
    When User logins with "invalid" credentials
    Then Verify error message "Invalid username/password" is displayed

  Scenario: 3. Verify that the system prevents users from logging in with invalid login details.
    Given Navigate to BuggyCars website
    When User enters the below data and clicks login
      | username | password |
      | test     |          |
      |          | rohit    |
      |          |          |

  Scenario: 4. Verify that the user is able to log out successfully after logging in.
    Given Navigate to BuggyCars website
    When User logins with "valid" credentials
    Then User is logged in successfully
    And  User is logged out successfully
