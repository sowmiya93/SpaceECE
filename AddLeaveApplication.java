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

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddLeaveApplication {

    private WebDriver driver;
    private final String baseUrl = "http://43.204.210.9/";

    @BeforeClass
    public void setup() {
        // Initialize WebDriver (Selenium will automatically find chromedriver from PATH)
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  // Remove implicit wait
        driver.manage().window().maximize();

        // Open the application URL
        driver.get(baseUrl);

        // Perform login
    }

    @Test(priority = 0)
    public void verifyLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Username']")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password' and @placeholder='Password']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Log In']")));

        // Input login credentials and submit
        usernameField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("admin123456");
        loginButton.click();
        System.out.println("Logged in as admin");
    }

    @Test(priority = 1)
    public void viewLeaveModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Leave module
        WebElement leaveModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Leave']")));
        leaveModule.click();
        System.out.println("Leave module clicked");
    }

    @Test(priority = 2)
    public void addLeave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement leaveApplicationLink = wait.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//a[@href='http://43.204.210.9/leave/Application' and normalize-space(text())='Leave Application']")
        	));
        	leaveApplicationLink.click();

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

       
        WebElement hourAmountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='hourAmountVal']")));
        hourAmountDropdown.click();
        WebElement fiveHourOption = wait.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//select[@id='hourAmountVal']/option[@value='7']")
        	));
        	fiveHourOption.click();

        // Enter Date for Leave: 9th Jan 2025
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='startdate']")));
        dateInput.clear();
        dateInput.sendKeys("13/01/2025");

        // Reason for leave
        WebElement reasonTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='reason']")));
        reasonTextarea.sendKeys("Casual leave, needs to rest");

        // Submit the application
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @class='btn btn-primary']")));
        submitButton.click();
    }
    @Test(priority = 2)
public void  accceptandReject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    	 WebElement rejectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-id='49' and text()='Reject']")));
         rejectButton.click();  // Reject the leave application for Jane Doe
         System.out.println("Leave application for Jane Doe rejected.");
         WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-id='50' and @data-value='Approve']")));
         approveButton.click();  // Approve the leave application for John Doe

         // Print message on successful approval
         System.out.println("Leave application for John Doe approved.");
}
 
    
}   
   

