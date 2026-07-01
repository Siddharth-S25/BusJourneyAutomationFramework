Feature: Bus Booking Flow

  Scenario: Search buses from Pune to Bangalore

    Given user launches AbhiBus application
    When user enters source city as "Pune"
    And user enters destination city as "Bangalore"
    And user selects today's date
    Then search results should be displayed
    And URL should contain source and destination
    And minimum buses should be displayed