
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class attendence {
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

        // Perform login if necessary
        login();
    }

    @Test(priority = 0)
    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Username']")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password' and @placeholder='Password']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Log In']")));

        // Input login credentials and submit
        usernameField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("admin123456");
        loginButton.click();
        System.out.println("Login as admin");
    }

    @Test(priority = 1)
    public void accessAttendanceModule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Attendance module
        WebElement attendanceModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'hide-menu') and normalize-space(text())='Attendance']")));
        attendanceModule.click();
        System.out.println("Attendance module is clicked");

        // Click on Attendance List
        WebElement attendanceList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/attendance/Attendance']")));
        attendanceList.click();
        System.out.println("Attendance List opened");
    }

    @Test(priority = 2)
    public void addAttendance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Add Attendance page
        WebElement addAttendanceButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='http://43.204.210.9/attendance/Save_Attendance' and contains(text(), 'Add Attendance')]")));
        addAttendanceButton.click();

        // Select Employee Name
        WebElement employeeSelect = driver.findElement(By.xpath("//select[@name='emid']"));
        Select employeeDropdown = new Select(employeeSelect);
        employeeDropdown.selectByVisibleText("abhay pawar");

        // Select Attendance Date
        WebElement dateField = driver.findElement(By.xpath("//input[@name='attdate']"));
        dateField.sendKeys("01/07/2025"); // Example date

        // Set Sign-in time
        WebElement signInField = driver.findElement(By.xpath("//input[@name='signin']"));
        signInField.sendKeys("09:30 ");  // Example sign-in time

        // Select Sign-in time from clock picker (optional)
        WebElement clockpickerSignIn = driver.findElement(By.xpath("//div[@class='clockpicker-dial clockpicker-minutes']"));
        clockpickerSignIn.click();
        WebElement selectSignInTime = driver.findElement(By.xpath("//div[contains(@style, 'left: 87px; top: 7px')]"));
        selectSignInTime.click();

        // Set Sign-out time
        WebElement signOutField = driver.findElement(By.xpath("//input[@name='signout']"));
        signOutField.sendKeys("06:30 ");  // Example sign-out time

      
        // Select Place (Office/Field)
        WebElement placeSelect = driver.findElement(By.xpath("//select[@name='place']"));
        Select placeDropdown = new Select(placeSelect);
        placeDropdown.selectByVisibleText("office");

        // Submit the attendance form
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='attendanceUpdate']"));
        submitButton.click();
        System.out.println("Attendance submitted");
    }

    @Test(priority = 3)
    public void verifyAttendanceSubmission() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Verify if the attendance is submitted correctly by checking the confirmation message or page
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Attendance added successfully')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Attendance submission failed");
        System.out.println("Attendance added successfully");
    }}

