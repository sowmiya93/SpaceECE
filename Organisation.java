

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Organisation {
	
	    private WebDriver driver;
	    private final String baseUrl = "http://hrms.spaceforece.com/Spacece-HRMS/";

	    @BeforeClass
	    public void setup() {
	        // Initialize WebDriver (Selenium will automatically find chromedriver from PATH)
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.manage().window().maximize();

	        // Open the application URL
	        driver.get(baseUrl);

	        // Perform login
	     
	    }
	    @Test(priority = 0)
	    public void verifyLogin() {
	        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
	        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
	        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Log In']"));

	        // Input login credentials and submit
	        usernameField.sendKeys("admin@gmail.com");
	        passwordField.sendKeys("admin12345");
	        loginButton.click();
	        System.out.println("login as admin");
	    }


    @Test(priority = 1)
    public void testAccessOrganizationModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on the "Organization" module using XPath
        WebElement organizationModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Organization']")));
        organizationModule.click();
        System.out.println("Organization module is clicked");

        // Step 2: Click on "Department" and verify content
        WebElement department = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/organization/Department' and contains(text(),'Department')]")));
        department.click();
        System.out.println("Department details are viewed");

       
        // Step 3: Click on "Designation" and verify content
        WebElement designation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/organization/Designation' and contains(text(),'Designation')]")));
        designation.click();
        System.out.println("Designation details are viewed");

    }

    @Test(priority = 3)
    public void testAddNewDepartment() {
        
        WebElement DepartmentnameField = driver.findElement(By.xpath("//input[@type='text' and @id='firstName' and @name='department' and contains(@class, 'form-control')]"));
        DepartmentnameField.sendKeys("champion");
         WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        System.out.println("new department is added");
        
    } 
    @Test(priority = 4)
    public void testViewExistingDepartments() {
        // Step 1: Click on the 'Department' link and wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement department = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/organization/Department' and contains(text(),'Department')]")));
        department.click();

        // Step 2: Verify the Department list is displayed
        WebElement departmentList = driver.findElement(By.xpath("//h4[@class='m-b-0 text-white' and normalize-space(text())='Department List']"));
        Assert.assertTrue(departmentList.isDisplayed(), "Department list is not displayed");
        System.out.println("Department list is displayed");
    }
    @Test(priority = 5)
    public void testAddNewDesignation() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement designation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/organization/Designation' and contains(text(),'Designation')]")));
        designation.click();
        
        // Step 2: Fill form and submit
        WebElement designationNameField = driver.findElement(By.xpath("//input[@name='designation']"));
        designationNameField.sendKeys("Champion Intern");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        // Step 3: Verify new designation
        WebElement newDesignation = driver.findElement(By.xpath("//td[contains(text(), 'Champion Intern')]"));
        Assert.assertTrue(newDesignation.isDisplayed(), "New designation is not added");
        System.out.println("new designation is added");
        
    }
    @Test(priority = 6)
    public void testViewExistingDesignations() {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement designation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/organization/Designation' and contains(text(),'Designation')]")));
        designation.click();
       

        // Step 2: Verify designation list
        WebElement designationList = driver.findElement(By.xpath("//h4[@class='m-b-0 text-white' and normalize-space(text())='Designation List']\r\n"
        		+ ""));
        Assert.assertTrue(designationList.isDisplayed(), "Designation list is not displayed");
        System.out.println("Designation is displayed");
    }
   

}
