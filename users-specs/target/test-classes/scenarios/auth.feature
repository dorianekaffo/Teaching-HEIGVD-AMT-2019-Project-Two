Feature: Testing CRUD operations for the users API

  Background:
    Given There a User server and auth server

  Scenario: Login to the server
    Given I have a the email "dorianekaffo@gmail.com" and a password "administrator"
    When I "POST" it to the /login endpoint
    Then I receive a 200 status code and a token

  Scenario: Logout to the server
    Given I am logged to the system
    When I "POST" it to the /logout endpoint
    Then I receive a 200 status code