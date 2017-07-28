package step_definitions;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepDefs{
    public WebDriver driver;
	String baseURL = "https://weather-acceptance.herokuapp.com/";
	String homepageTitle = "5 Weather Forecast";
	
    public StepDefs()
    {
    	driver = Hooks.driver;
    }
    
    @When("^I open the Weather forecast app$")
    public void i_open_the_weather_forecast_app() throws Throwable {
    	// Open the app URL
    	driver.get(baseURL);
    	
    	// Check correct page is returned
    	String actualPageTitle = driver.getTitle();
    	assertEquals(homepageTitle, actualPageTitle);
    }

    @Then("^the Five day forecast is returned$")
    public void the_five_day_forecast_is_returned() throws Throwable {
    	// Count the Day page elements
    	int elementCount = driver.findElements(By.cssSelector("span.name")).size();
    	// Check there are 5 day elements
    	assertEquals(5, elementCount);
    	
    	// Note this step only checks that there are 5 page elements with the specified CSS selection.  This is just a simple check given the time allowed.
    }

    @And("^I enter the city (.+)$")
    public void i_enter_a(String city) throws Throwable {
    	driver.findElement(By.id("city")).clear();
    	driver.findElement(By.id("city")).sendKeys(city);
    }

    @And("^click return key$")
    public void click_return_key() throws Throwable {
       	driver.findElement(By.id("city")).sendKeys(Keys.RETURN);
    }
    
    @Then("^the 3 hourly forecast for that day is displayed$")
    public void the_3_hourly_forecast_for_that_day_is_displayed() throws Throwable {
    	// There are only 4 x 3 hour segments shown for the first day
    	// Assume this is due to current test system time - Potential missing requirement
    	// Hard code to look for those four whilst missing requirement is investigated
    	String threeHourSeg1 = driver.findElement(By.xpath("//span[@data-test='hour-1-1']")).getText();
    	String threeHourSeg2 = driver.findElement(By.xpath("//span[@data-test='hour-1-2']")).getText();
    	String threeHourSeg3 = driver.findElement(By.xpath("//span[@data-test='hour-1-3']")).getText();
    	String threeHourSeg4 = driver.findElement(By.xpath("//span[@data-test='hour-1-4']")).getText();
    
    	assertEquals("1300", threeHourSeg1);
    	assertEquals("1600", threeHourSeg2);
    	assertEquals("1900", threeHourSeg3);
    	assertEquals("2200", threeHourSeg4);
    }

    @Then("^the 3 hourly forecast for that day is hidden$")
    public void the_3_hourly_forecast_for_that_day_is_hidden() throws Throwable {
    	
    	// Adding a simple fixed wait here to account for the time taken to collapse the expanded view
    	// Given more time would implement a better solution such as polling the table row properties before continuing
    	Thread.sleep(1000);
    	
    	// Collect all the hour segment page elements in a list
    	List<WebElement> hourElementsList = driver.findElements(By.xpath("//span[@class='hour']"));
		
    	// Iterate through the list checking that none are currently displayed
    	for (WebElement hourElement : hourElementsList) {
    	    assertFalse(hourElement.isDisplayed());
    	}
    }


    @And("^select a day.*")     // Matches both steps
    public void select_a_day() throws Throwable {
    	driver.findElement(By.cssSelector("span.name")).click();
    }

    @Then("^the daily forecast looks like this$")
    public void the_daily_forecast_looks_like_this(DataTable weatherTable) throws Throwable {
    	
    	// Allow table to load
    	// Given more time would implement a wait which checks if "checking the skies" text is still displayed
    	Thread.sleep(1000);
    	
    	// Convert datatable into something usable
		List<List<String>> data = weatherTable.raw();
		
		// For each row check the data tables matches the frontend
		for (int i=1; i<6; i++) {
		// Check Days
		String expectedDay = data.get(i-1).get(0); 
    	String actualDay = driver.findElement(By.xpath("//span[@data-test='day-"+i+"']")).getText();
		assertEquals(actualDay, expectedDay);
		
		// TO DO : same check for all other data columns
		
		}
    }
  
}