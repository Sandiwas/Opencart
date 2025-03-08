package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_002_LoginDDT extends BaseClass {
 
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "dataDriven")  // getting Data provider form  different clas
	public void verify_Login(String email, String pwd, String exp) throws InterruptedException {
//		try {
		logger.info("**** Starting TC_002_LoginTest Test");

		HomePage hp = new HomePage(getDriver());
		hp.clickMyAccount();
		hp.clickLogin();
		Thread.sleep(5000);

		LoginPage logp = new LoginPage(getDriver());
		logp.setEmail(email);
		logp.setPassword(pwd);
		logp.clickLogin();

		MyAccountPage myap = new MyAccountPage(getDriver());
		boolean targetPage = myap.isMyAccountPageExist();

		if (exp.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				myap.clickLogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				myap.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		// } //catch (Exception e) {
//			Assert.fail();
//		}
		logger.info("**** Finished TC_002_LoginTest Test");
	}
}
