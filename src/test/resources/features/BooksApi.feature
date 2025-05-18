@book @smoke @crud
Feature: Book API Operations

  This feature tests the CRUD operations for managing books via the Book API.

  @create @positive
  Scenario: Create a new book
    Given a new book with title "Clean Code", author "Robert C. Martin", and year 2008
    When I send a POST request to create the book
    Then the book should be created with status code 201

  @read @positive @regression
  Scenario: Retrieve the created book by ID
    Given a new book with title "The Pragmatic Programmer", author "Andrew Hunt", and year 1999
    When I send a POST request to create the book
    Then the book should be created with status code 201
    When I retrieve the book by ID
    Then the response should contain the title "The Pragmatic Programmer"

  @update @positive @regression
  Scenario: Update the book title
    Given a new book with title "Refactoring", author "Martin Fowler", and year 1999
    When I send a POST request to create the book
    Then the book should be created with status code 201
    When I update the book title to "Refactoring (2nd Edition)"
    Then the book should be updated with status code 200
    When I retrieve the book by ID
    Then the response should contain the title "Refactoring (2nd Edition)"

  @delete @positive @regression
  Scenario: Delete an existing book
    Given a new book with title "Domain-Driven Design", author "Eric Evans", and year 2003
    When I send a POST request to create the book
    Then the book should be created with status code 201
    When I delete the book by ID
    Then the book should be deleted with status code 204

  @read @negative
  Scenario: Fetch a non-existent book by ID
    When I try to fetch a non-existent book ID
    Then the book should be deleted with status code 404

