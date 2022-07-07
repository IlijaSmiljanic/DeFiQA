package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PortfolioPage {

    private WebDriver driver;
    private WebDriverWait wdwait;


    private String sideMenuXpath = "//*[@class='links-wrapper']";

    private String makerDAOXpath = "//a[@href='/development/makerdao']";

    private String tokenValueXpath = "//*[@class='token-info-value']";

    public PortfolioPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }
    public WebElement sideMenu(){
        return driver.findElement(By.xpath(sideMenuXpath));
    }

    public WebElement makerDAO(){
        return driver.findElement(By.xpath(makerDAOXpath));
    }

    public WebElement tokenValue(){
        return driver.findElement(By.xpath(tokenValueXpath));
    }

    public void goToMakerDAO()
    {
        makerDAO().click();
    }
}
