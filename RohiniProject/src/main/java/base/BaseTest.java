package base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import util.ExtentManager;
import util.PropertyFileReader;

public class BaseTest {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void suiteLevelSetup(){
		extent = ExtentManager.getReporter(PropertyFileReader.getProperty("reportPath"));
	}
	
	@BeforeMethod
	public void intiateTest(Method method) {
		test=extent.startTest(this.getClass().getSimpleName()+" :: "+method.getName(),"Start of Test : "+method.getName());
		test.assignAuthor("Ram");
		test.log(LogStatus.PASS,"Test method have been started");
		driver = getDriver();
	}
	
	@AfterMethod
	public void methodEnd(ITestResult result){
		
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test.log(LogStatus.FAIL, "Exception is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}else{
			test.log(LogStatus.PASS,"Test have been passed");
		}
		extent.endTest(test);
		extent.flush();
		killDriver();
	}
	
	@AfterSuite
	public void endSuiteLevelSetup(){
		extent.close();
	}
	
	public static WebDriver getDriver() {
		
		if(driver == null){
			String browser = PropertyFileReader.getProperty("browser").trim().toLowerCase();
			DesiredCapabilities capabilities = null;
			switch (browser) {

			case "firefox":
				System.setProperty("webdriver.gecko.driver", "binaries/geckodriver.exe");
				capabilities = DesiredCapabilities.firefox();
				FirefoxOptions fOptions = new FirefoxOptions();
				fOptions.setLogLevel(Level.SEVERE);
				capabilities.setCapability("moz:firefoxOptions", fOptions);
				driver = new FirefoxDriver(capabilities);
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", "binaries/chromedriver.exe");
				ChromeOptions cOptions = new ChromeOptions();
				Map<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_setting_values.notifications", 2);
				chromePrefs.put("credentials_enable_service", false);
				cOptions.setExperimentalOption("prefs", chromePrefs);
				cOptions.addArguments("disable-infobars");
				driver = new ChromeDriver(cOptions);
				break;

			case "ie":
				System.setProperty("webdriver.ie.driver", "binaries/IEDriverServer.exe");
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
				driver = new InternetExplorerDriver(capabilities);
				break;
			
			case "edge":
				System.setProperty("webdriver.ie.driver", "binaries/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				break;
			default:
				Assert.fail();
			}

			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
		
		return driver;
	}

	public static void killDriver() {
		driver.quit();
		driver = null;
	}

}
