Feature: Block user

  Background:
    Given I have a user payload with username and password
    When I POST it to the /registrations endpoint
    Then I receive a 201 status code
    When I POST his credentials to the /authentications endpoint
    Then I receive a 200 status code
    Then I receive a jwt token
    Given there is an admin
    Given there is an admin token

  Scenario: Block user
    When admin blocks user
    Then I receive a 200 status code
    When I POST his credentials to the /authentications endpoint
    Then I receive a 200 status code
    Then I receive a jwt token
    Given I have a jwt token
    Given I have a profile update
    When I PUT a profile update to the /profileUpdates endpoint
    Then I receive a 403 status code