package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MakerDAOPage {

    public WebDriver driver;
    public WebDriverWait wdwait;

    private String createCDP = "//a[@href='/development/makerdao/create-cdp']";

    private String collateralXpath = "//*[@name='collAmount']";

    private String debtXpath = "//*[@name='daiAmount']";

    private String createButtonXpath = "//*[@form='create-cdp-form']";

    private String acceptButtonXpath = "//*[text()='Accept']";

    private String tranCompleteXpath = "//*[@class='description']";

    private String messageTitleXpath = "//*[contains(text(), 'created')]";

    private String automateButtonXpath = "//*[contains(text(), 'Automate')]";

    private String balanceLeftXpath = "//*[@class='balance']/div/span";

    private String loaderXpath = "//*[@class='message-wrapper']";

    private String loadingCDPXpath = "//*[@class='loader']";

    private String customizeGasXpath = "//*[@class='title-advanced']";

    private String gasPriceXpath = "//*[@name='gasPrice']";

    private String gasLimitXpath = "//*[@name='gasLimit']";

    private String lowGasPriceWarningXpath = "//*[@class='warning-box-wrapper     ']/span";


    public MakerDAOPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    private void invisibilityWait(WebElement element) {wdwait.until(ExpectedConditions.invisibilityOf(element));}
    private void visibilityWait(WebElement element) {
        wdwait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement createCDPbutton()
    {
        return driver.findElements(By.xpath(createCDP)).get(0);
    }

    public WebElement newCDPbutton()
    {
        return driver.findElements(By.xpath(createCDP)).get(1);
    }

    public WebElement collateralTextBox()
    {
        return driver.findElement(By.xpath(collateralXpath));
    }

    public WebElement customizeGasSettings()
    {
        return driver.findElement(By.xpath(customizeGasXpath));
    }

    public WebElement debtTextBox()
    {
        return driver.findElement(By.xpath(debtXpath));
    }

    public WebElement createButton()
    {
        return driver.findElement(By.xpath(createButtonXpath));
    }

    public WebElement acceptButton()
    {
        return driver.findElements(By.xpath(acceptButtonXpath)).get(0);
    }

    public WebElement transactionResult()
    {
        return driver.findElements(By.xpath(tranCompleteXpath)).get(0);
    }

    public WebElement createCDPresult()
    {
        return driver.findElements(By.xpath(tranCompleteXpath)).get(1);
    }

    public WebElement verifySuccessMessage()
    {
        return driver.findElement(By.xpath(messageTitleXpath));
    }

    public WebElement automateButton()
    {
        return driver.findElements(By.xpath(automateButtonXpath)).get(1);
    }

    public WebElement gasPrice()
    {
        return driver.findElement(By.xpath(gasPriceXpath));
    }

    public WebElement gasLimit()
    {
        return driver.findElement(By.xpath(gasLimitXpath));
    }

    public WebElement gasPriceWarning()
    {
        return driver.findElement(By.xpath(lowGasPriceWarningXpath));
    }

    public WebElement balanceLeft()
    {
        return driver.findElement(By.xpath(balanceLeftXpath));
    }

    public WebElement loader()
    {
        return driver.findElement(By.xpath(loaderXpath));
    }

    public WebElement loadingCDP()
    {
        return driver.findElement(By.xpath(loadingCDPXpath));
    }


    public void checkUserBalance(String balance)
    {

        Assert.assertEquals(balanceLeft().getText(), balance);
    }

    public void createNewCdp(String collateral, String debt)
    {
        collateralTextBox().sendKeys("50");

        //clear debt textbox
        debtTextBox().clear();

        //add 20000 DAI to debt
        debtTextBox().sendKeys("20000");

        //click create
        createButton().click();
    }

    public void createCdp()
    {
        acceptButton().click();
    }

    public void verifySuccessfulSmartWalletTransaction()
    {
        Assert.assertTrue(transactionResult().getText().contains("completed"));
    }

    public void verifySuccessfulCdpTransaction()
    {
        Assert.assertTrue(createCDPresult().getText().contains("completed"));
    }

    public void clickAutomateButton()
    {
        automateButton().click();
    }
}
