Feature: Creation of actors

  Background:
    Given there is an application server
    Given I have a jwt token

  Scenario: create a new actor and then delete it
    Given I have a valid actor payload
    When I POST it to the /actors endpoint
    Then I receive a 201 status code
    When I DELETE it in the /actors/actorsId endpoint
    Then I receive a 200 status code

  Scenario: create a new actor and then update it
    Given I have a valid actor payload
    When I POST it to the /actors endpoint
    Then I receive a 201 status code
    When I UPDATE it in the /actors/actorsId endpoint
    Then I receive a 200 status code

  Scenario: create a new actor and then find it
    Given I have a valid actor payload
    When I POST it to the /actors endpoint
    Then I receive a 201 status code
    When I FIND it in the /actors/actorsId endpoint
    Then I receive a 200 status code

  Scenario: get all actors
    When I GET all actors
    Then I receive a 200 status code
    Then I receive a list of actors
    When I GET all actors with pagination parameters
    Then I receive a list of actors with the specified pagination size