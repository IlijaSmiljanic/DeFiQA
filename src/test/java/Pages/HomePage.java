package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePage {
    public WebDriver driver;
    public WebDriverWait wdwait;
    private String startSimBtnXpath = "//button[@class='button green']";
    private String newAccountBtnXpath = "//button[@class='button green highlighted']";
    private String acceptCookiesBtnId = "privacy-popup-accept";

    private String closeTitleXpath = "//*[@class='close-icon']";


    private void invisibilityWait(WebElement element) {wdwait.until(ExpectedConditions.invisibilityOf(element));}
    private void visibilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public HomePage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement simulationButton()
    {
        return driver.findElement(By.xpath(startSimBtnXpath));
    }

    public WebElement newAccountButton()
    {
        return driver.findElement(By.xpath(newAccountBtnXpath));
    }

    public WebElement acceptCookiesButton()
    {
        return driver.findElement(By.id(acceptCookiesBtnId));
    }

    public WebElement closeTitleButton() { return driver.findElement(By.xpath(closeTitleXpath));}

    public void clickabilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public void dismissCookies()
    {
        //closes bookmark
        closeTitleButton().click();
        //accept cookies
        acceptCookiesButton().click();
    }

    public void startSimulationWithNewUser()
    {
        //start simulation
        simulationButton().click();
        //new aacount
        visibilityWait(newAccountButton());
        newAccountButton().click();
    }
}
