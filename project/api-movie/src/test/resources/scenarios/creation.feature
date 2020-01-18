Feature: Creation of movies

  Background:
    Given there is a Movies server

  Scenario: create a movie
    Given I have a user payload with username and password
    When I POST it to the /registrations endpoint
    Then I receive a 201 status code
