import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardTests {

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

        // Perform login 
    }
 // Verify login success
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
    public void verifyDashboardPageLoad() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement dashboardContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='hide-menu' and text()='Dashboard ']")));
        dashboardContent.click();
        System.out.println("Dashboard content is visible.");
    }

    @Test(priority = 2)
    public void verifyEmployeeSection() {
        System.out.println("Waiting for Employee box to be clickable...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the 'View Employee' link to be clickable
        WebElement employeeBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View Employee']")));

        // Click the 'View Employee' link
        System.out.println("Clicking on Employee box...");
        employeeBox.click();

        // Wait for the Employee list to be visible
        System.out.println("Waiting for Employee list to be visible...");
        WebElement employeeList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Employee List']")));
        Assert.assertTrue(employeeList.isDisplayed(), "Employee list did not load properly.");
        System.out.println("Employee list is visible.");
    }

    @Test(priority = 3)
    public void verifyLeaveSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate back to Dashboard
        System.out.println("Navigating back to Dashboard...");
        WebElement dashboardLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='hide-menu' and text()='Dashboard ']")));
        dashboardLink.click();
        System.out.println("Dashboard page loaded again.");

        // Verify Leave Section
        System.out.println("Navigating to Leave section...");
        WebElement leaveSectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View Leave']")));
        leaveSectionLink.click();

        WebElement leaveList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space(text())='Application List']")));
        Assert.assertTrue(leaveList.isDisplayed(), "Leave section did not load properly.");
        System.out.println("Leave section is visible.");
    }
    @Test(priority = 4)
    public void verifyProjectSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate back to Dashboard
        System.out.println("Navigating back to Dashboard...");
        WebElement dashboardLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='hide-menu' and text()='Dashboard ']")));
        dashboardLink.click();
        System.out.println("Dashboard page loaded again.");

        // Verify Project Section
        System.out.println("Navigating to Project section...");
        WebElement projectSectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View Project']")));
        projectSectionLink.click();

        WebElement projectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space(text())='Project List']")));
        Assert.assertTrue(projectList.isDisplayed(), "Project section did not load properly.");
        System.out.println("Project section is visible.");
    }
    @Test(priority = 5)
    public void verifyLoanSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate back to Dashboard
        System.out.println("Navigating back to Dashboard...");
        WebElement dashboardLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='hide-menu' and text()='Dashboard ']")));
        dashboardLink.click();
        System.out.println("Dashboard page loaded again.");

        // Verify Project Section
        System.out.println("Navigating to loan section...");
        WebElement projectSectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='View Loans']")));
        projectSectionLink.click();

        WebElement projectList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space(text())='Loan List']")));
        Assert.assertTrue(projectList.isDisplayed(), "Project section did not load properly.");
        System.out.println("Project section is visible.");
    }

      
    @Test(priority = 6)
    public void verifyUserProfileSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to 'My Profile' via the dropdown
        WebElement profileDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='nav-link dropdown-toggle text-muted waves-effect waves-dark' and @data-toggle='dropdown']")));
        profileDropdown.click();

        WebElement myProfileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/employee/view?I=RG9lMTc1Mw==']")));
        myProfileLink.click();

       

        // Verify profile image
        WebElement profileImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='img-circle' and @width='150']")));
        Assert.assertTrue(profileImage.isDisplayed(), "Profile image is not displayed!");

        // Verify profile name
        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='card-title m-t-10' and text()='Jhon Doe']")));
        Assert.assertEquals(profileName.getText(), "Jhon Doe", "Profile name is incorrect!");
    }


    
    @Test(priority = 7)
    public void verifyAddNewTaskButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Verify "To Do list" header
        WebElement todoListHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='card-title' and text()='To Do list']")));
        Assert.assertTrue(todoListHeader.isDisplayed(), "'To Do list' header is not visible!");

        // Enter a new task in the input field
        WebElement taskInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='todo_data' and @placeholder='Add new task']")));
        taskInputField.sendKeys("Automated Test Task");

        // Click the "Add New Task" button
        WebElement addTaskButton = driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'todo-submit')]"));
        addTaskButton.click();

        // Verify the new task is added to the list
        WebElement newTaskLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[span[text()='Automated Test Task']]")));
        Assert.assertTrue(newTaskLabel.isDisplayed(), "The new task was not added to the 'To Do list'!");

        // Mark the task as completed
        WebElement taskCheckbox = newTaskLabel.findElement(By.xpath("./preceding-sibling::input[@type='checkbox']"));
        taskCheckbox.click();

        // Verify the task is marked as completed (strikethrough or similar effect)
        WebElement completedTask = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[span[text()='Automated Test Task']]/span[contains(@style, 'line-through')]")));
        Assert.assertTrue(completedTask.isDisplayed(), "The task was not marked as completed!");
    }

    }
