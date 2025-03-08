package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups ={"sanity","Master"})
	public void verify_Login() {
		try 
		{
			logger.info("**** Starting TC_002_LoginTest Test");

			HomePage hp = new HomePage(getDriver());
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage logp = new LoginPage(getDriver());
			logp.setEmail(pro.getProperty("email"));
			logp.setPassword(pro.getProperty("password"));
			logp.clickLogin();

			MyAccountPage myap = new MyAccountPage(getDriver());
			boolean targetPage = myap.isMyAccountPageExist();

			Assert.assertTrue(targetPage); // Assert.assertEquals(targetPage, true, "Login failed");
		} 
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished TC_002_LoginTest Test");
	}
}
