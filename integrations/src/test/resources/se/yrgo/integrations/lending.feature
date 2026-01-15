Feature: Lending out a books
As a administrator I want to lend books to users so that users can borrow and keep track of their loan 

  Scenario: Lending out a book
    Given an "administrator" is logged in.
    And navigates to "Handle loans".
    And the book with id "1234" is available.
    And the user with id "5678" exists.
    When the administrator lends book "1234" to user "5678".
    Then the book "1234" should be registered as loaned to user "5678".

  Scenario: Look at borrowed books
    Given an "user" is logged in.
    And navigates to "My Books".
    When the user has at least one loan.
    Then the user see the text "My book loans".