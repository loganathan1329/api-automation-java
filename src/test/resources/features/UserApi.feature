Feature: User API CRUD operations

  Scenario: Create a new user
    Given a user payload with name "John Doe" and email "john@example.com"
    When I send a POST request to "/users"
    Then the response status code should be 201
    And the response should contain the user name "John Doe"

  Scenario: Get user by ID
    Given an existing user is created
    When I send a GET request to "/users/{id}"
    Then the response status code should be 200
    And the response should contain the user email "john@example.com"

  Scenario: Update user
    Given an existing user is created
    When I send a PUT request to "/users/{id}" with updated name "Jane Doe"
    Then the response status code should be 200
    And the response should contain the user name "Jane Doe"

  Scenario: Delete user
    Given an existing user is created
    When I send a DELETE request to "/users/{id}"
    Then the response status code should be 204

  Scenario: Get non-existing user
    When I send a GET request to "/users/nonexistent"
    Then the response status code should be 404

