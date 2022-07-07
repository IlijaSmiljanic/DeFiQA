package Test;

import Base.BaseTest;
import Pages.MakerDAOPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(Base.TestNGListener.class)

public class MakeDAO extends BaseTest {


    @BeforeMethod
    public void pageSetUp(){

        driver.manage().window().maximize();
        driver.navigate().to("https://stageapp.defisaver.com/development/");
    }



    @Test
    public void test() throws InterruptedException
    {
        //closes bookmark
        homePage.closeTitleButton().click();

        //accept cookies
        homePage.acceptCookiesButton().click();

        //start simulation
        homePage.simulationButton().click();

        //new aacount
        visibilityWait(homePage.newAccountButton());
        homePage.newAccountButton().click();

        //wait for loader to finish
        invisibilityWait(makerDAOPage.loader());

        //value doesn't update on time so we need to wait for a second
        //check user balance is 100ETH
        Thread.sleep(500);
        visibilityWait(makerDAOPage.balanceLeft());
        Assert.assertEquals(makerDAOPage.balanceLeft().getText(), "100.00 ETH");

        //go to makerDAO page
        portfolioPage.makerDAO().click();

        //start new cdp
        makerDAOPage.createCDPbutton().click();

        //add 50 ETH to collateral
        Thread.sleep(500);
        visibilityWait(makerDAOPage.collateralTextBox());
        clickabilityWait(makerDAOPage.collateralTextBox());
        makerDAOPage.collateralTextBox().sendKeys("50");

        //clear debt textbox
        makerDAOPage.debtTextBox().clear();

        //add 20000 DAI to debt
        makerDAOPage.debtTextBox().sendKeys("20000");

        //click create
        makerDAOPage.createButton().click();

        //click accept creating smart wallet
        visibilityWait(makerDAOPage.loadingCDP());
        invisibilityWait(makerDAOPage.loadingCDP());
        clickabilityWait(makerDAOPage.acceptButton());
        makerDAOPage.acceptButton().click();

        //verify transaction is completed
        Thread.sleep(2000);
        Assert.assertTrue(makerDAOPage.transactionResult().getText().contains("completed"));

        //click accept creating CDP
        Thread.sleep(1000);
        makerDAOPage.acceptButton().click();

        //verify transaction is completed
        Thread.sleep(3000);
        Assert.assertTrue(makerDAOPage.createCDPresult().getText().contains("completed"));

        //verify that CDP is created successfully
        Thread.sleep(500);
        Assert.assertTrue(makerDAOPage.verifySuccessMessage().getText().contains("created successfully"));

        //click automate button
        makerDAOPage.automateButton().click();

        //verify remaining user balance
        Assert.assertEquals(makerDAOPage.balanceLeft().getText(), "49.94 ETH");
    }
}
