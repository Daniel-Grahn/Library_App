Feature: Searching for books
As a user I want to be able to search for available books so I know what I can loan.

  Scenario: Getting to the search page
    Given the user is on the start page.
    When the user navigates to the book search.
    Then they can see the search form.

  Scenario: Searching for nothing
    Given the user is on the search page.
    When the user submits an empty search.
    Then they see the message "No books found".

  Scenario: Searching for Astrid Lindgrens book
    Given the user is on the search page.
    When the user searches for isbn "9789129697285".
    Then they see "Astrid Lindgren" as the author.
   