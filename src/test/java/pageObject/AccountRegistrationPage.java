package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirtsName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephoneNo;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfrimPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkbPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirtsName(String fname) {
		txtFirtsName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);

	}

	public void setTelephoneNo(String tel) {
		txtTelephoneNo.sendKeys(tel);
	}

	public void setPassword(String tel) {
		txtPassword.sendKeys(tel);
	}

	public void setConfrimPassword(String confpwd) {
		txtConfrimPassword.sendKeys(confpwd);
	}

	public void setprivacyPolicy() {
		chkbPolicy.click();
	}

	public void clickContinue() {

		btnContinue.click();

		// sol1
		// btnContinue.submit();

		// sol2
		// Actions act= new Actions(driver);
		// act.moveToElement(btnContinue).click();

		// sol3
		// JavascriptExecutor js=(JavascriptExecutor)driver;
		// js.executeScript("arguments[0].click();", btnContinue);

		// sol4
		// btnContinue.sendKeys(Keys.RETURN);

		// sol5
		// WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public String getConfirmationMessage() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}

	}
}
