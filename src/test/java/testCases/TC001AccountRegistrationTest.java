package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import java.time.Duration;

public class TC001AccountRegistrationTest extends BaseClass{

    @Test(groups = {"regression","master"})
    public void verifyAccountRegistration(){
        //logger
       logger.info("******** Starting TC001AccountRegistrationTest **********");
       try {
           //Homepage
           HomePage homePage = new HomePage(driver);
           logger.info("******** Click on MyAccount link **********");
           homePage.clickMyAccount();
           logger.info("******** Click on register link **********");
           homePage.clickRegister();
           // Registration page
           AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

           logger.info("******** Providing customer details **********");
           accountRegistrationPage.setFirstName(randomString());
           accountRegistrationPage.setLastName(randomString());
           accountRegistrationPage.setEmail(randomString() + "@gmail.com");
           accountRegistrationPage.setTelephone(randomNumber());

           String password = randomAlphaNumber();
           accountRegistrationPage.setPassword(password);
           accountRegistrationPage.setConfirmPassword(password);

           accountRegistrationPage.setPrivacyPolicy();
           accountRegistrationPage.clickContinue();

           logger.info("******** Validation **********");
           String confirmMsg = accountRegistrationPage.getConfirmationMsg();
          Assert.assertEquals(confirmMsg, "Your Account Has Been Created!");

       }catch (Exception e){
           logger.error("Test failed");
           //logger.debug("Debug logs");
            Assert.fail();
       }
    }

}
