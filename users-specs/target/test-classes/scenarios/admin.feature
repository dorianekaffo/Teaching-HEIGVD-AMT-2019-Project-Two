Feature: Testing CRUD operations for the users API

  Background:
    Given There a User server and I am logged as admin
    And There is another user to block with id "celinedion@heigvd.ch"

  Scenario: Block a user as an admin by making a PUT request
    Given  I have the email "celinedion@heigvd.ch" of a user
    When I "PUT" to the /users/block/"email" endpoint
    Then I receive a 200 status code

  Scenario: Unblock a user as an admin by making a DELETE request
    Given I have the email of a user
    When I "DELETE" to the /users/block/"email" endpoint
    Then I receive a 200 status code




