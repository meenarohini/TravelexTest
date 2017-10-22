package tests;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.FindStorePage;
import pages.LandingPage;

public class FindStoreTest extends BaseTest{

	
	@Test(dataProvider="postCodeData")
	public void findStore(String postCode){
		LandingPage landing = new LandingPage(driver);
		landing.openURL("https://www.travelex.co.uk/");
		FindStorePage findStore = landing.openStoreFinder();
		findStore.enterPostCode(postCode);
		findStore.findStores();
		List<String> stores = findStore.getFoundStores();
		if(stores.size()!=0){
			test.log(LogStatus.INFO,"Below are the store for postcode : "+postCode);
			for(String store:stores){
				test.log(LogStatus.INFO, store);
			}
		}else{
			test.log(LogStatus.INFO, "No store found for postcode : "+postCode);
		}
		
	}
	
	@DataProvider(name="postCodeData")
	public Object[][] getPostCode(){
		Object[][] countryData = {{"EC1A 1BB"},{"W1A 0AX"},{"DN55 1PT"}};
		return countryData;
	}
}
