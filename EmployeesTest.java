import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployeesTest {
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
	    public void viewemployeelist() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	WebElement EmployeesModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Employees']")));
	        EmployeesModule.click();
	        System.out.println("Employees  module is clicked");
	        WebElement Employeedetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/employee/Employees' and normalize-space(text())='Employees']")));
	        Employeedetails.click();
	        WebElement employeelist = driver.findElement(By.xpath("//h4[contains(@class, 'text-white') and contains(text(), 'Employee List')]"));
	        Assert.assertTrue(employeelist.isDisplayed(), "employees list is not displayed");
	        System.out.println("employee list is displayed");
	    }
	    @Test(priority =2)     
	    	 public void testAddEmployee() {
	    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	        WebElement Employeedetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/employee/Employees' and normalize-space(text())='Employees']")));
	                Employeedetails.click();
	    	        driver.findElement(By.xpath("//a[@href='http://43.204.210.9/employee/Add_employee']")).click();
	    	              

	    	        // Fill in employee details
	    	        driver.findElement(By.xpath("//input[@name='fname']")) .sendKeys("Sowmi");
	    	             
	    	        driver.findElement(By.xpath("//input[@name='lname']")).sendKeys("Balu");
  	              
	    	        driver.findElement(By.xpath("//input[@name='eid']")).sendKeys("123");
	    	              
	    	        driver.findElement(By.xpath("//select[@name='dept']")).sendKeys("Quality Analyst");
	    	        driver.findElement(By.xpath("//select[@name='deg']")).sendKeys("QA");
	    	        driver.findElement(By.xpath("//select[@name='role']")).sendKeys("employee");
	    	        driver.findElement(By.xpath("//select[@name='gender']")).sendKeys("Female");
	    	        driver.findElement(By.xpath("//select[@name='blood']")).sendKeys("B+"); 
	    	        driver.findElement(By.xpath("//input[@name='contact']")).sendKeys("9696969692");
	    	        driver.findElement(By.xpath("//input[@name='nid']")).sendKeys("1234568874");

	    	        driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("06-08-1994"); 
	    	        driver.findElement(By.xpath("//input[@name='joindate']")).sendKeys("09-11-2024");
	    	        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("sowmibalu94");
	    	        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sowmibalu@gmail.com");
	    	        driver.findElement(By.xpath("//input[@name='image_url']")).sendKeys("D:\\1sowmi\\SPAC Internship\\humpro.jpg");
	    	        driver.findElement(By.xpath("//button[text()=' Save']")).click();
	    	    }
	    @Test(priority = 3)
	    public void editEmployeeDetails() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Navigate to the Employees section
	        WebElement EmployeesModule = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Employees']")));
	        EmployeesModule.click();
	        System.out.println("Employees module is clicked");

	        // Open Employee List
	        WebElement Employeedetails = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[@href='http://43.204.210.9/employee/Employees' and normalize-space(text())='Employees']")));
	        Employeedetails.click();
	        System.out.println("Employee details are opened");

	        // Navigate to the 5th page directly
	        WebElement page5Button = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[@class='paginate_button ' and text()='5']")));
	        page5Button.click();
	        System.out.println("Navigated to page 5 of employee list");

	        // Locate the Edit button for the first employee on the 5th page
	        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[@href='http://43.204.210.9/employee/view?I=MDExNTMz' and @title='Edit']")));
	        editButton.click();
	        System.out.println("Edit button clicked for the  employee on page 5");

	        // Edit employee details on the new page
	        WebElement lastNameField = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//input[@name='lname']")));
	        lastNameField.clear();
	        lastNameField.sendKeys("pandey");

	        WebElement bloodGroupField = driver.findElement(By.xpath("//select[@name='blood']"));
	        bloodGroupField.sendKeys("O-");

	        // Save the updated details
	        WebElement saveButton = driver.findElement(By.xpath("//button[text()=' Save']"));
	        saveButton.click();
	        System.out.println("Employee details updated successfully");

	        	    }
	    @Test(priority = 4)
	    public void viewEmployeeListAndDownloadAllFiles() {
	        // Navigate to the Employees section and open the Employee List
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Click on Employees module
	        WebElement EmployeesModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Employees']")));
	        EmployeesModule.click();

	        // Open Employee details page
	        WebElement EmployeeDetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/employee/Employees' and normalize-space(text())='Employees']")));
	        EmployeeDetails.click();

	        // Verify Employee List page is opened
	        WebElement employeeList = driver.findElement(By.xpath("//h4[contains(@class, 'text-white') and contains(text(), 'Employee List')]"));
	        Assert.assertTrue(employeeList.isDisplayed(), "Employee list is not displayed");

	        // Now perform the download test for Excel, CSV, and PDF files
	        downloadAllFiles();  // Download all files (Excel, CSV, PDF)
	    }

	    // Method to download all files (Excel, CSV, PDF)
	    public void downloadAllFiles() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // List of file types to download (case-insensitive)
	        String[] fileTypes = {"excel", "csv", "pdf"};
	        
	        // Loop through each file type and initiate the download
	        for (String fileType : fileTypes) {
	            WebElement downloadButton = getDownloadButton(wait, fileType);

	            // Click the download button
	            if (downloadButton != null) {
	                downloadButton.click();
	                System.out.println(fileType.toUpperCase() + " file download initiated.");

	                // Wait for the download to complete (adjust this as needed)
	                try {
	                    Thread.sleep(5000); // Adjust sleep time based on download speed
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }

	                // Print success message
	                System.out.println(fileType.toUpperCase() + " file downloaded successfully.");
	            }
	        }
	    }

	    // Helper method to get the download button based on file type
	    public WebElement getDownloadButton(WebDriverWait wait, String fileType) {
	        WebElement downloadButton = null;

	        // Locate the download button based on the file type using XPath
	        switch (fileType.toLowerCase()) {
	            case "excel":
	                downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'buttons-excel') and span[text()='Excel']]"))); 
	                break;
	            case "csv":
	                downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'buttons-csv') and span[text()='CSV']]"))); 
	                break;
	            case "pdf":
	                downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'buttons-pdf') and span[text()='PDF']]"))); 
	                break;
	            default:
	                System.out.println("Invalid file type!");
	                return null;
	        }

	        return downloadButton;
	    }

	        

	    @Test(priority = 5)
	    public void testEmployeeNameSorting() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Step 1: Click on the Employee Name column header to sort (ascending)
	        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//th[@class='sorting' and text()='Employee Name']"))); // Sort button for ascending order
	        sortButton.click(); // Trigger the sort action
	        System.out.println("Employee names are sorted correctly in ascending order.");
	        WebElement sortButton2 = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//th[@class='sorting_asc' and @aria-sort='ascending' and text()='Employee Name']"))); // Sort button for ascending order
		        sortButton2.click();
		        System.out.println("Employee names are sorted correctly in decending  order.");
	    }
	    @Test(priority = 6)
	    public void testrequiredAddEmployee() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        WebElement employeeDetails = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/employee/Employees' and normalize-space(text())='Employees']")));
	        employeeDetails.click();
	        
	        driver.findElement(By.xpath("//a[@href='http://43.204.210.9/employee/Add_employee']")).click();

	        // Leave first name, phone number and email blank
	        driver.findElement(By.xpath("//input[@name='fname']")).sendKeys(""); // Empty first name
	        driver.findElement(By.xpath("//input[@name='lname']")).sendKeys("Balu");
	        driver.findElement(By.xpath("//input[@name='eid']")).sendKeys("123");

	        driver.findElement(By.xpath("//select[@name='dept']")).sendKeys("Quality Analyst");
	        driver.findElement(By.xpath("//select[@name='deg']")).sendKeys("QA");
	        driver.findElement(By.xpath("//select[@name='role']")).sendKeys("employee");
	        driver.findElement(By.xpath("//select[@name='gender']")).sendKeys("Female");
	        driver.findElement(By.xpath("//select[@name='blood']")).sendKeys("B+");

	        // Leaving phone number blank
	        driver.findElement(By.xpath("//input[@name='contact']")).sendKeys("858585"); // Empty phone number

	        driver.findElement(By.xpath("//input[@name='nid']")).sendKeys("1234568874");
	        driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("06-08-1994");
	        driver.findElement(By.xpath("//input[@name='joindate']")).sendKeys("09-11-2024");

	        // Leave email blank
	        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(""); // Empty email

	        driver.findElement(By.xpath("//input[@name='image_url']")).sendKeys("D:\\1sowmi\\SPAC Internship\\humpro.jpg");
	        
	        // Click Save
	        driver.findElement(By.xpath("//button[text()=' Save']")).click();

	        // Capture and assert error messages for missing first name, phone number, and email

	        WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='fname' and @class='error']")));
	        WebElement phoneNumberError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='contact' and @class='error']")));
	        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='example-email2' and @class='error']")));

	        // Capture the error message text
	        String firstNameErrorMessage = firstNameError.getText();
	        String phoneNumberErrorMessage = phoneNumberError.getText();
	        String emailErrorMessage = emailError.getText();

	        // Optionally print the captured error messages for debugging purposes
	        System.out.println("First Name Error: " + firstNameErrorMessage);
	        System.out.println("Phone Number Error: " + phoneNumberErrorMessage);
	        System.out.println("Email Error: " + emailErrorMessage);
	    }
	   @Test(priority = 7)
	    public void testAddDisciplinary() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement EmployeesModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Employees']")));
	        EmployeesModule.click();
	        // Navigate to the Disciplinary page
	        WebElement disciplinaryLink = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[@href='http://43.204.210.9/employee/Disciplinary' and normalize-space(text())='Disciplinary']")));
	        disciplinaryLink.click();

	        // Click on Add Disciplinary button
	        WebElement addDisciplinaryButton = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[contains(@class, 'btn-info') and .//a[text()=' Add Disciplinary ']]")));
	        addDisciplinaryButton.click();

	        // Wait for the modal to load
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='exampleModal']")));

	        // Fill in the disciplinary details
	        Select employeeDropdown = new Select(driver.findElement(By.name("emid")));
	        employeeDropdown.selectByValue("def1524"); // Replace "Soy1332" with the desired employee ID

	        Select warningDropdown = new Select(driver.findElement(By.name("warning")));
	        warningDropdown.selectByValue("Writing Warning"); // Replace "Verbel Warning" with the desired warning type

	        WebElement titleInput = driver.findElement(By.id("recipient-name1"));
	        titleInput.sendKeys("writing warning to abcd"); // Replace with the desired title

	        WebElement detailsInput = driver.findElement(By.id("message-text1"));
	        detailsInput.sendKeys("consider this warning as last ,donot make this again ok"); // Replace with the actual details

	        // Submit the form
	        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Submit']"));
	        submitButton.click();

	       System.out.println("Disciplinary action was not added successfully.");
	    }
	   @Test(priority = 8)
	   public void testEditDisciplinary() {
	       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	       // Wait until the modal is no longer displayed (if it's present)
	       try {
	           WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleModal")));
	           if (modal.isDisplayed()) {
	               // Wait for the modal to disappear before clicking the Edit button
	               wait.until(ExpectedConditions.invisibilityOf(modal));
	           }
	       } catch (Exception e) {
	           // If no modal appears, we continue
	           System.out.println("No modal found, continuing with Edit action.");
	       }

	       // Locate the Edit button and click it
	       WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//a[@title='Edit' and @data-id='11']")));
	       editButton.click();

	       // Update the disciplinary details
	       WebElement titleInput = driver.findElement(By.id("recipient-name1"));
	       titleInput.clear();
	       titleInput.sendKeys("no worries we are here for you"); // Replace with the updated title

	       WebElement detailsInput = driver.findElement(By.id("message-text1"));
	       detailsInput.clear();
	       detailsInput.sendKeys("no worries the dispilnary action has to be taken by our side."); // Replace with updated details

	       // Submit the updated form
	       WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit' and text()='Submit']"));
	       submitButton.click();

	       System.out.println("Disciplinary updated successfully");
	   }
	   @Test(priority = 9)
	   public void testDeleteDisciplinary() {
	       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	       // Wait until the modal is no longer displayed (if it's present)
	       try {
	           WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleModal")));
	           if (modal.isDisplayed()) {
	               // Wait for the modal to disappear before clicking the Delete button
	               wait.until(ExpectedConditions.invisibilityOf(modal));
	           }
	       } catch (Exception e) {
	           // If no modal appears, we continue
	           System.out.println("No modal found, continuing with Delete action.");
	       }

	       // Locate the Delete button and click it
	       WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//a[@href='DeletDisiplinary?D=14']")));
	       deleteButton.click();

	       // Handle the confirmation dialog
	       driver.switchTo().alert().accept(); // Click 'OK' on the confirmation alert

	       System.out.println("Disciplinary deleted successfully");
	   }



	        }
	    


	    

	    
	    

