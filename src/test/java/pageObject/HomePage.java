package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	// Wait until the element is clickable (common wait function)
	
    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
	  
    public void clickMyAccount() throws InterruptedException {
		waitForElementToBeClickable(lnkMyAccount);
		lnkMyAccount.click();
	}

	public void clickRegister() {
		waitForElementToBeClickable(lnkRegister);
		lnkRegister.click();
	}
	
	public void clickLogin() {
		waitForElementToBeClickable(lnkLogin);
		lnkLogin.click();
	}
}
