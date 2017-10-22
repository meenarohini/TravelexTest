package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.ElementActions;

public class BasePage extends ElementActions{

	private By travelMoney = By.linkText("Travel money");
	private By buyCurrency = By.partialLinkText("Buy currency");
	
	private By stores = By.linkText("Stores");
	private By storeFinder = By.partialLinkText("Store finder");
	
	public BasePage(WebDriver driver){
		super(driver);
	}
	
	public BuyCurrencyPage openBuyCurrency(){
		mouseHover(travelMoney);
		clickOn(buyCurrency);
		return new BuyCurrencyPage(driver);
	}
	
	public FindStorePage openStoreFinder(){
		mouseHover(stores);
		clickOn(storeFinder);
		return new FindStorePage(driver);
	}
	
}
