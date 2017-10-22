package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindStorePage extends BasePage{
	
	private By findStoreTextBox = By.id("searchTerm");
	private By findStoreButton  = By.id("searchBtn");
	
	private By storeList = By.xpath("//ul[@data-storepickupfilter='pickup']//h4");
	
	public FindStorePage(WebDriver driver){
		super(driver);
	}
	
	public void enterPostCode(String postCode){
		sendText(findStoreTextBox, postCode);
	}
	
	public void findStores(){
		clickOn(findStoreButton);
		waitInSeconds(5);
	}
	
	public List<String> getFoundStores(){
		
		return getMultipleText(storeList);
		
	}
}
