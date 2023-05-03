Feature: I can create a hotel reservation as a user

  Scenario: The user can create and delete a hotel reservation
    Given User tries a new hotel reservation
    And User gives the information for the reservation
    When User creates a hotel reservation
    Then Reservation has been succesfull
    And User deletes created reservation