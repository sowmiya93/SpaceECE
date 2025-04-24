import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import io.netty.handler.timeout.TimeoutException;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LeaveTest {

	
		 private WebDriver driver;
		    private final String baseUrl = "http://43.204.210.9/";

		    @BeforeClass
		    public void setup() {
		        // Initialize WebDriver (Selenium will automatically find chromedriver from PATH)
		        driver = new ChromeDriver();
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        driver.manage().window().maximize();

		        // Open the application URL
		        driver.get(baseUrl);

		        // Perform logins
		     
		    }
		    @Test(priority = 0)
		    public void verifyLogin() {
		        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
		        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Log In']"));

		        // Input login credentials and submit
		        usernameField.sendKeys("admin@gmail.com");
		        passwordField.sendKeys("admin123456");
		        loginButton.click();
		        System.out.println("login as admin");
		    
		    }
    @Test(priority = 1)
    
    public void viewLeaveModule() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

         // Navigate to Attendance module
         WebElement leaveModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Leave']")));
         leaveModule.click();
         System.out.println("leave module is clicked");
         
    } 

  @Test(priority = 2)
    public void navigateToHolidayPage() {
        // Navigate to the Holiday page
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement holidayPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/leave/Holidays']")));
        holidayPage.click();
    }
  @Test(priority = 3)
  public void addbutton() {
  // Click on Add Holiday button
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  WebElement addHolidayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn-info')]//a[@data-target='#holysmodel']")));
  addHolidayButton.click();
  System.out.println("Add Holiday button clicked.");
  }
  @Test(priority = 4)
  
  public void selectDateAndMoveToNextField() {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


          // Step 1: Fill in the holiday name (e.g., Pongal Holiday)
          WebElement holidayNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='holiname']")));
          holidayNameField.sendKeys("Pongal Holiday");
          System.out.println("Holiday name entered: Pongal Holiday");

       


	    // Click the input field to open the calendar
	    WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='startdate']")));
	    dateField.click();
	    System.out.println("Calendar opened");

	    // Wait until the calendar is visible
	    WebElement calendarTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'datepicker-days')]")));

	    // Select the day 14
	    WebElement desiredDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='14']")));
	    desiredDay.click();
	    System.out.println("Day 14 selected");

	    // Now, proceed to another field (for example, end date field)
	    WebElement endDateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='enddate']")));
	    endDateField.click();  // Click on the end date field
	    WebElement calendarTable2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'datepicker-days')]")));
	    WebElement desiredDay2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='day' and text()='15']")));
	    desiredDay2.click();
	    System.out.println("Day 15 selected");
	    WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='btn btn-primary']")));
	    submitButton.click();
	    System.out.println("holiday added sucessfully");
	}
  @Test(priority = 5)

  public void addLeaveType() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
     /* WebElement dashboardContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='hide-menu' and text()='Dashboard ']")));
      dashboardContent.click();
     
     WebElement leaveModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Leave']")));
      leaveModule.click();*/
	    try {
	        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='holysmodel']//button[@class='close']")));
	        closeModalButton.click();
	    } catch (Exception e) {
	        System.out.println("No modal to close");
	    }

      // Click the Leave Types link to open the leave types page
      WebElement leaveTypeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/leave/leavetypes']")));
      leaveTypeLink.click();

      // Click the "Add Leave" button to open the modal pop-up
      WebElement addLeaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-toggle='modal' and @data-target='#leavemodel']")));
      addLeaveButton.click();

      // Fill in the Leave Name field (for example: "Hourly Leave")
      WebElement leaveNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='leavename']")));
      leaveNameField.sendKeys("Hourly Leave");

      // Fill in the Leave Day field (for example: "10")
      WebElement leaveDayField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='leaveday']")));
      leaveDayField.sendKeys("10");

      // Select the "Active" status from the drop-down menu
      WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='status']")));
      statusDropdown.click();
      WebElement activeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='1']"))); // Active option
      activeOption.click();

      // Click the "Submit" button to save the leave type
      WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='btn btn-primary']")));
      submitButton.click();
      System.out.println("leave type added");
  }

  @Test(priority = 6)
  public void FetchtotalLeave() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        // Wait and close the modal if it's visible
	        WebElement closeModalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='holysmodel']//button[@class='close']")));
	        closeModalButton.click();
	    } catch (Exception e) {
	        System.out.println("No modal to close");
	    }

	    // Navigate to Leave Application module
	    WebElement leaveAppLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@class, 'btn btn-primary')]//a[@href='http://43.204.210.9/leave/Application' and contains(@class, 'text-white')]")));
	    leaveAppLink.click();

	    // Click on the "Add Application" button
	    WebElement addAppButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-toggle='modal' and @data-target='#appmodel' and contains(@class, 'text-white')]")));
	    addAppButton.click();

	    // Apply leave for "Abimanu Pandey" with "Sick Leave" for 5 hours on 9th Jan 2025
	    // Select Employee: Abimanu Pandey
	    WebElement employeeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='emid']")));
	    employeeDropdown.click();
	    WebElement employeeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='011533']"))); // Abimanu Pandey
	    employeeOption.click();

	    // Select Leave Type: Sick Leave
	    WebElement leaveTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='typeid']")));
	    leaveTypeDropdown.click();
	    WebElement sickLeaveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='2']"))); // Sick Leave
	    sickLeaveOption.click();

	    // Click on Fetch Total Leave Button
	    WebElement fetchTotalLeaveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'fetchLeaveTotal')]")));
	    fetchTotalLeaveButton.click();

	    // Fetch the leave balance message
	    WebElement leaveBalanceMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='total']")));
	    String message = leaveBalanceMessage.getText();
	    System.out.println("Leave Balance Message: " + message);  
	    // This will print the leave balance message
	    WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @class='btn btn-default' and @data-dismiss='modal']")));
        closeButton.click();
  }
 
}


  

