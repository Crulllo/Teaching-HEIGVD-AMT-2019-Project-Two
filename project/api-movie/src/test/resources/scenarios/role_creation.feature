Feature: Creation of roles

  Background:
    Given there is an application server
    Given I have a jwt token

  Scenario: create a new role and then delete it
    Given I have a valid role payload
    When I POST it to the /roles endpoint
    Then I receive a 201 status code
    When I DELETE it in the /roles/rolesId endpoint
    Then I receive a 200 status code

  Scenario: get all roles by movie
    When I GET all roles by movie
    Then I receive a 200 status code
    Then I receive a list of roles
    When I GET all roles with pagination parameters
    Then I receive a list of roles with the specified pagination size

  Scenario: get all roles by actor
    When I GET all roles by actor
    Then I receive a 200 status code
    Then I receive a list of roles
    When I GET all roles with pagination parameters
    Then I receive a list of roles with the specified pagination size