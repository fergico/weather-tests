# Feature file create to test the requirement -
#			- Enter city name, get 5 day weather forecast
#
# All available test data is tested via the scenario outline

Feature: Enter city name, get 5 day weather forecast

  Scenario Outline: Enter city name
    When I open the Weather forecast app
    And I enter the city <CITY>
    And click return key
    Then the Five day forecast is returned

    Examples: 
      | CITY      |
      | aberdeen  |
      | edinburgh |
      | glasgow   |
      | perth     |
      | stirling  |
