package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import testCases.BaseClass;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = BaseClass.getDriver();
		PageFactory.initElements(driver, this);
	}

}
