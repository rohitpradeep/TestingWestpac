Feature: Profile update for BuggyCars

  Scenario: 1. Verify user can update profile
    Given Navigate to registration page
    Then User can with register with all valid details
    And User can login with registered details
    And User is logged in successfully
    Then Navigate to profile page
    When Below details are updated in user profile
      | age | firstname | lastname | address  | phone    | hobby   | gender |passwordchange|
      | 15  | Roh       | RO       | 123 Test | 12345678 | Reading | Male   |Yes           |


