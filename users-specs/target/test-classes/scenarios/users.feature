Feature: Testing operation on the Users API

  Background:
    Given I am logged

  Scenario: Create a user using POST request
    Given I have a User payload
    When I POST it to the /users endpoint
    Then uI receive a 200 status code

  Scenario: Get a user using GET request
    Given I have a the email of a user
    When I GET it to the /users/"email" endpoint
    Then I receive a 200 status code and a user payload

  Scenario: Update a user using PUT request
    Given I have a User payload
    When I PUT it to the /users endpoint
    Then uI receive a 200 status code

  Scenario: Delete a user using DELETE request
    Given I have a User payload
    When I DELETE it to the /users/"email" endpoint
    Then I receive a 200 status code with a feedback