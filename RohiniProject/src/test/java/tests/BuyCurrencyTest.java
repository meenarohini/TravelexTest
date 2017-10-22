package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.BuyCurrencyPage;
import pages.LandingPage;

public class BuyCurrencyTest extends BaseTest{

	@Test(dataProvider="countryData")
	public void buyCurrency(String country, int currencyAmount){
		LandingPage landing = new LandingPage(driver);
		landing.openURL("https://www.travelex.co.uk/");
		BuyCurrencyPage buyCurrency = landing.openBuyCurrency();
		buyCurrency.selectCurrencyOrCountry(country);
		buyCurrency.enterCurrencyAmount(currencyAmount);
		test.log(LogStatus.INFO,"Entered currency Amount  is  : "+currencyAmount);
		test.log(LogStatus.INFO,"Entered country is  : "+country);
		test.log(LogStatus.INFO,"Converted currency Amount  is  : "+buyCurrency.getConvertedCurrency());
		Assert.assertTrue(buyCurrency.isCashModeSelected());
		buyCurrency.clickOnBuyCash();
	}
	
	@DataProvider(name="countryData")
	public Object[][] getCountry(){
		Object[][] countryData = {{"Australia",3000},{"Canada",2000},{"USA",2500}};
		return countryData;
	}
	
	
}
