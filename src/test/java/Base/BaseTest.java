package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BaseTest {


    public WebDriver driver;
    public WebDriverWait wdwait;


    public HomePage homePage;
    public PortfolioPage portfolioPage;
    public MakerDAOPage makerDAOPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(15));

        this.homePage = new HomePage(driver, wdwait);
        this.portfolioPage = new PortfolioPage(driver, wdwait);
        this.makerDAOPage = new MakerDAOPage(driver, wdwait);

    }


    public void invisibilityWait(WebElement element) {wdwait.until(ExpectedConditions.invisibilityOf(element));}
    public void visibilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickabilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }



    public void highlightElement(WebElement element)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }
}

