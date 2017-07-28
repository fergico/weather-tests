# Feature file create to test the requirements -
#			Daily forecast should summarise the 3 hour data:
#					- Most dominant (or current) condition
#					- Most dominant (or current) wind speed and direction
#					- Aggregate rainfall
#					- Minimum and maximum temperatures
#					- All values should be rounded down

Feature: Daily forecast data

  Scenario: Daily forecast data is pulled and displayed from source correctly
    When I open the Weather forecast app
    And I enter the city glasgow
    And click return key
    Then the daily forecast looks like this
      | Tue | 20 | 16 |  7 | 3kph  | 1mm  |
      | Wed | 20 | 14 |  5 | 1kph  | 6mm  |
      | Thu | 20 | 13 |  9 | 4kph  | 15mm |
      | Fri | 20 | 13 |  9 | 6kph  | 4mm  |
      | Sat | 20 | 13 | 11 | 11kph | 40mm |

# Future development - Carry out for all data sets
