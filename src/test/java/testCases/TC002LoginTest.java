package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002LoginTest extends BaseClass{

    @Test(groups = {"sanity","master"})
    public void verifyLogin(){
        logger.info("***** starting TC002LoginTest ******** ");
        try {
            //Home page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();
            //Login page
            LoginPage lp = new LoginPage(driver);
            // lp.setEmail(properties.getProperty("email"));
            lp.setEmail("email");
            lp.setPassword(properties.getProperty("password"));
            lp.clickLogin();

            // Acoount page
            MyAccountPage ap = new MyAccountPage(driver);
            boolean targetPage = ap.isMyAccountPageExists();

            // Assert.assertEquals(targetPage,true,"Login failed");
            Assert.assertTrue(targetPage);
            ap.clkLogout(); //click logout link
        }catch (Exception e){
            Assert.fail();
        }
        logger.info("**** TC02 Finish *****");
    }

}
