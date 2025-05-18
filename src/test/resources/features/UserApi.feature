@user @smoke @create
Feature: User API Operations

  This feature verifies the complete CRUD operations of the User API.

  Background:
    Given a user payload with name "Jane Doe" and email "jane.doe@example.com"

  @create @positive
  Scenario: Create a new user
    When I send a POST request to "/users"
    Then the response status code should be 201
    And the response should contain the user name "Jane Doe"
    And the response should contain the user email "jane.doe@example.com"

  @read @positive @regression
  Scenario: Retrieve an existing user by ID
    Given an existing user is created
    When I send a GET request to "/users/{id}"
    Then the response status code should be 200
    And the response should contain the user name "John Doe"
    And the response should contain the user email "john@example.com"

  @update @positive @regression
  Scenario: Update an existing user's name
    Given an existing user is created
    When I send a PUT request to "/users/{id}" with updated name "John Smith"
    Then the response status code should be 200
    And the response should contain the user name "John Smith"
    And the response should contain the user email "john@example.com"

  @delete @positive @regression
  Scenario: Delete an existing user
    Given an existing user is created
    When I send a DELETE request to "/users/{id}"
    Then the response status code should be 204

  @create @negative
  Scenario: Create a user with missing name
    Given a user payload with name "" and email "missingname@example.com"
    When I send a POST request to "/users"
    Then the response status code should be 400

  @create @negative
  Scenario: Create a user with invalid email format
    Given a user payload with name "Invalid Email" and email "invalid-email"
    When I send a POST request to "/users"
    Then the response status code should be 400

  @read @negative
  Scenario: Get user with non-existent ID
    Given an existing user is created
    When I send a GET request to "/users/invalid-id"
    Then the response status code should be 404

  @update @negative
  Scenario: Update user with invalid ID
    Given a user payload with name "Update Fail" and email "fail@example.com"
    When I send a PUT request to "/users/invalid-id" with updated name "Updated Name"
    Then the response status code should be 404

  @delete @negative
  Scenario: Delete user with invalid ID
    When I send a DELETE request to "/users/invalid-id"
    Then the response status code should be 404
