package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4jimport org.openqa.selenium.OutputType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	public static WebDriver driver;
	public Logger logger; // This variable is going to generate the logs for every test cases.
	public Properties pro;

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	@BeforeClass(groups = { "sanity", "regression", "Master", "dataDriven" })
	@Parameters({"browser", "OS"})
	public void setup(String br, String os) throws IOException {

		// Loading Config.properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		pro = new Properties();
		pro.load(file);

		logger = LogManager.getLogger(this.getClass()); // This we help to load the log4j2 file

		// code for remote execution
		if (pro.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// OS
			if (os.equalsIgnoreCase("windows")){
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No matching os");
				return;
			}
			
		/*	switch (os.toLowerCase()) {
			case "windows": capabilities.setPlatform(Platform.WIN11); break;
			case "mac": capabilities.setPlatform(Platform.MAC); break;
			default: System.out.println("No matching os");; return;
			} */

			// browser select on node
			switch (br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

		ChromeOptions co=new ChromeOptions();
		co.addArguments("--headless");
		FirefoxOptions fo=new FirefoxOptions();
		fo.addArguments("--headless");
		EdgeOptions eo=new EdgeOptions();
		eo.addArguments("--headless");
		
		if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(co); break;
			case "edge": driver = new EdgeDriver(eo); break; 
			case "firefox": driver = new FirefoxDriver(fo); break;
			default:System.out.println("Invalid browser"); return;
			}
		}
		
		/*	if (pro.getProperty("execution_env").equalsIgnoreCase("local")) {
				switch (br.toLowerCase()) {
				case "chrome": driver = new ChromeDriver(); break;
				case "edge": driver = new EdgeDriver(); break; 
				case "firefox": driver = new FirefoxDriver(); break;
				default:System.out.println("Invalid browser"); return;
				}
		} */
			
		threadLocalDriver.set(driver);
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(pro.getProperty("appUrl")); // reading url from properties file.
		getDriver().manage().window().maximize();
	}

	@AfterClass(groups = {"sanity", "regression", "Master","dataDriven"})
	public void tearDown() {
		getDriver().quit();
	}
	

	public String randomString() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;

	}

	public String randomNumber() {
		String generateNumeric = RandomStringUtils.randomNumeric(10);
		return generateNumeric;
	}

	public String randomAlphaNumeric() {
		String generateNumeric = RandomStringUtils.randomAlphabetic(3);
		String generateString = RandomStringUtils.randomNumeric(3);
		return (generateString + "+@+" + generateNumeric);
	}

	public String captureScreenshot(String tName) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) BaseClass.getDriver();
		File sourcesFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tName + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilepath);

		sourcesFile.renameTo(targetFile);
		return targetFilepath;
	}

}

//FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");