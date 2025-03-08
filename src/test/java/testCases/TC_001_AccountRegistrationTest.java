package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups ={"regression","Master"})
	public void verify_account_Registration() {
		try {
			logger.info("*** Starting TC_001_AccountRegistrationTest ***");

			HomePage hp = new HomePage(getDriver());

			hp.clickMyAccount();
			logger.info(" clicked on MyAccount Link");
			Thread.sleep(2000);
			hp.clickRegister();
			logger.info(" clicked on Register Link");

			AccountRegistrationPage regP = new AccountRegistrationPage(getDriver());
			logger.info("Providing customer details");
			regP.setFirtsName(randomString().toUpperCase());
			regP.setLastName(randomString().toUpperCase());
			regP.setEmail(randomString() + "@gmail.com");
			regP.setTelephoneNo(randomNumber());

			String password = randomAlphaNumeric();

			regP.setPassword(password);
			regP.setConfrimPassword(password);
			regP.setprivacyPolicy();
			regP.clickContinue();

			String confmsg = regP.getConfirmationMessage();

			logger.info("Validating expected message");

			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test Failed...");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
			// Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*** Finished TC_001_AccountRegistrationTest ****");
	}

}
