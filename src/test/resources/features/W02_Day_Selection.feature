# Feature file create to test the requirements -
#			- Select day, get 3 hourly forecast
#			- Select day again, hide 3 hourly forecast



Feature: Enter city name, get 5 day weather forecast

  Scenario: Select Day
    When I open the Weather forecast app
    And select a day
    Then the 3 hourly forecast for that day is displayed
    
    
    Scenario: Select Day Again
    When I open the Weather forecast app
    And select a day
    Then the 3 hourly forecast for that day is displayed
    And select a day again
    Then the 3 hourly forecast for that day is hidden