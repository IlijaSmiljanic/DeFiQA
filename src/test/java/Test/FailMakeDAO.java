package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Base.TestNGListener.class)

public class FailMakeDAO extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){

        driver.manage().window().maximize();
        driver.navigate().to("https://stageapp.defisaver.com/development/");
    }

    @Test
    public void test2() throws InterruptedException
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

        makerDAOPage.customizeGasSettings().click();
        makerDAOPage.gasPrice().clear();
        makerDAOPage.gasPrice().sendKeys("-20");

        Assert.assertEquals(makerDAOPage.gasPriceWarning().getText(),"Using lower than cheap gas price can result in stuck transactions.");

        makerDAOPage.acceptButton().click();


        makerDAOPage.gasPrice().clear();
        makerDAOPage.gasPrice().sendKeys("1");

        makerDAOPage.acceptButton().click();

        Thread.sleep(2000);
        Assert.assertTrue(makerDAOPage.transactionResult().getText().contains("completed"));

        makerDAOPage.customizeGasSettings().click();
        makerDAOPage.gasLimit().clear();
        makerDAOPage.gasLimit().sendKeys("-20");

        makerDAOPage.acceptButton().click();


        makerDAOPage.gasLimit().clear();
        makerDAOPage.gasLimit().sendKeys("1");

        makerDAOPage.acceptButton().click();

        Thread.sleep(1000);
        visibilityWait(makerDAOPage.createCDPresult());
        Assert.assertEquals(makerDAOPage.createCDPresult().getText(), "Returned error: error simulating transaction");
    }
}
