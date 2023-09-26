import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
    }

    @BeforeEach
    void setupAll() {
        driver = new ChromeDriver();
    }

    // Steps 1-6: Text box
    @Test
    public void testTextBox() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Elements")).click();
        driver.findElement(By.name("Text Box")).click();
        driver.findElement(By.id("userName")).sendKeys("Иван Иванов");
        driver.findElement(By.id("userEmail")).sendKeys("ivanov@ivanov.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Москва, ул. Тверская, дом 1, квартира 1");
        driver.findElement(By.id("permanentAddress")).sendKeys("Москва, ул. Тверская, дом 1, квартира 1");
        driver.findElement(By.cssSelector("button[type = 'submit']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("дом"));
    }

    //  Steps 7-13: Buttons
    @Test
    public void testButtonClickMe() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Elements")).click();
        driver.findElement(By.id("item-4")).click();
        driver.findElement(By.cssSelector("button[type = 'Click Me']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("You have done a dynamic click"));
    }

    @Test
    public void testButtonRightClickMe() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Elements")).click();
        driver.findElement(By.id("item-4")).click();
        WebElement element = driver.findElement(By.id("rightClickBtn"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        Assert.assertTrue(driver.getCurrentUrl().contains("You have done a right click"));
    }

    @Test
    public void testButtonDoubleClickMe() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Elements")).click();
        driver.findElement(By.id("item-4")).click();
        WebElement element = driver.findElement(By.id("doubleClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        Assert.assertTrue(driver.getCurrentUrl().contains("You have done a double click"));
    }

    //    Steps 14-30 : Alerts, Frame & Windows
    @Test
    public void testNewTab() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Browser Windows")).click();
        WebElement button = driver.findElement(By.id("tabButton"));
        button.click();
        driver.get("https://demoqa.com/sample");
        Assert.assertTrue(driver.getCurrentUrl().contains("This is a sample page"));
        driver.close();
    }

    @Test
    public void testNewWindow() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Browser Windows")).click();
        WebElement button = driver.findElement(By.id("windowButton"));
        button.click();
        driver.get("https://demoqa.com/sample");
        Assert.assertTrue(driver.getCurrentUrl().contains("This is a sample page"));
        driver.close();
    }

    @Test
    public void testAlerts() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Alerts")).click();
        WebElement button = driver.findElement(By.id("alertButton"));
        button.click();
        String popupWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(popupWindowHandle);
        button.click();
    }

    @Test
    public void testAlerts5sec() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Alerts")).click();
        WebElement button = driver.findElement(By.id("timerAlertButton"));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("This alert appeared after 5 seconds")));
        String popupWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(popupWindowHandle);
        button.click();
    }

    @Test
    public void testAlertsConfirmBox() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Alerts")).click();
        WebElement button = driver.findElement(By.id("confirmButton"));
        button.click();
        String popupWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(popupWindowHandle);
        driver.findElement(By.cssSelector("button[type = 'OK']")).click();
        driver.close();
        driver.get("https://demoqa.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("You selected Ok"));
        driver.close();
    }

    @Test
    public void testAlertsPromtBox() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.name("Alerts, Frame & Windows")).click();
        driver.findElement(By.name("Alerts")).click();
        WebElement button = driver.findElement(By.id("promptButton"));
        button.click();
        String popupWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(popupWindowHandle);
        WebElement textBox = driver.findElement(By.id("Please enter your name"));
        textBox.sendKeys("Test name");
        driver.findElement(By.cssSelector("button[type = 'OK']")).click();
        driver.close();
        driver.get("https://demoqa.com/");
        Assert.assertTrue(driver.getCurrentUrl().contains("You entered Test name"));
        driver.close();
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }
}