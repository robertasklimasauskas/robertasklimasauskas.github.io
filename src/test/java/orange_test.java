import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class orange_test {
    WebDriver driver;

    @BeforeClass
    @Parameters({"browser"})
    void setup(String browser) throws Exception {

        switch (browser) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

    }

//    @Parameters({"usernamebad", "passwordbad"})
//    @Test
//    void NegativeOrangeTest(String usernamebad, String passwordbad) throws Exception {
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys(usernamebad);
//        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(passwordbad);
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
//
//
//}


    @Parameters({"username", "password", "usernamesecond", "passworduser"})
    @Test
    void PositiveOrangeTest(String username, String password, String usernamesecond, String passworduser) throws Exception {
        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[type=\"submit\"]")).click();
        Thread.sleep(2000);

        // admin paspaudimas ir sukurimas naujo vartotojo
        driver.findElement(By.cssSelector(".oxd-main-menu-item")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--secondary:nth-child(1)")).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-select-text-input' and text()='-- Select --']")));
        userRoleDropdown.click();
        WebElement essOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='ESS']")));
        essOption.click();

        Thread.sleep(3000);

        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-select-text-input' and text()='-- Select --']")));
        statusDropdown.click();
        WebElement enabledOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Enabled']")));
        enabledOption.click();

        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        inputField.sendKeys("K");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@role='option'])[1]")));
        firstOption.click();


        driver.findElement(By.cssSelector(".oxd-input.oxd-input--active:nth-child(1)")).sendKeys(usernamesecond);
        driver.findElement(By.cssSelector("[type=\"password\"]")).sendKeys(passworduser);
        WebElement secondPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='password'])[2]")));
        secondPasswordField.sendKeys("admin1634");

        WebElement SaveButton = driver.findElement(By.cssSelector("[type=submit]"));
        SaveButton.click();

        Thread.sleep(5000);

        WebElement secondTrashIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[contains(@class, 'oxd-icon bi-trash')])[2]")));
        secondTrashIcon.click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")).click();


    }


}
