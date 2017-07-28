package step_definitions;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks{
    public static WebDriver driver;

    
    @Before
 
    public void openBrowser() throws MalformedURLException {
    	
    	
    	System.out.println("Starting Browser");
    	
    	// Open chrome browser
    	System.setProperty("webdriver.chrome.driver", "C:\\Automation_Resources\\chromedriver.exe");
    	driver = new ChromeDriver();

    	// Can alternatively use firefox using the code below
    	//System.setProperty("webdriver.gecko.driver", "C:\\Automation_Resources\\geckodriver.exe");
    	//driver = new FirefoxDriver();
    	
    	// Delete cookies to avoid share state between tests
    	driver.manage().deleteAllCookies();
    	// Maximise window
    	driver.manage().window().maximize();
    }

     
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
       
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        
        }
        driver.quit();
        
    }
    
}