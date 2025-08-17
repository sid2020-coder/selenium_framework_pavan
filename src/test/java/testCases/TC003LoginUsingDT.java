package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC003LoginUsingDT extends BaseClass{

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = {"datadriven"})
    public void verifyLoginDT(String email,String pwd,String exp){
      logger.info("**** starting TC003*******");
      try {
          //Homepage
          HomePage hp = new HomePage(driver);
          hp.clickMyAccount();
          hp.clickLogin();
          //Login page
          LoginPage lp = new LoginPage(driver);
          lp.setEmail(email);
          lp.setPassword(pwd);
          lp.clickLogin();
    /*
    Data valid - login success - test pass - logout
                  login failed - test fail
    Data invalid - login success - test fail - logout
                    login failed - test pass
     */
          // Acoount page
          MyAccountPage ap = new MyAccountPage(driver);
          boolean targetPage = ap.isMyAccountPageExists();

          if (exp.equalsIgnoreCase("valid")) {
              if (targetPage == true) {
                  ap.clkLogout();
                  Assert.assertTrue(true);
              } else {
                  Assert.assertTrue(false);
              }
          }
          if (exp.equalsIgnoreCase("invalid")) {
              if (targetPage == true) {
                  ap.clkLogout();
                  Assert.assertTrue(false);

              } else {
                  Assert.assertTrue(true);
              }
          }
      }catch (Exception e){
          Assert.fail();
      }
      logger.info("***** Finish TC003 ******");
    }

}
