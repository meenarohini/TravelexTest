package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BuyCurrencyPage extends BasePage{
	
	private By currencyAutoComplete = By.name("currency");
	private String currencyByCountry_xpath = "//ul[contains(@class,'autocomplete')]//li[./*[contains(text(),'%s')]][1]";
	
	private By inputCurrencyTxtBox = By.id("x-buy-amount");
	private By convertedCurrencyTxtBox = By.id("x-cost");
	private By cashCurrencyModeRadio = By.id("x-cash");
	private By buyCashButton = By.xpath("//button[contains(@class,'addCash')]");
	
	
	public BuyCurrencyPage(WebDriver driver){
		super(driver);
		waitForPageToBeLoaded();
	}
	
	public void selectCurrencyOrCountry(String currencyOrCountry){
		waitInSeconds(5);
		clickOn(currencyAutoComplete);
		clickOn(By.xpath(String.format(currencyByCountry_xpath, currencyOrCountry)));
	}
	
	public void enterCurrencyAmount(int currencyAmount){
		sendText(inputCurrencyTxtBox, String.valueOf(currencyAmount));
	}
	
	public String getConvertedCurrency(){
		return getAttribute(convertedCurrencyTxtBox, "value");
	}
	
	public boolean isCashModeSelected(){
		boolean isCashMode=false;
		String selectedAttribute = getAttribute(cashCurrencyModeRadio,"checked");
		if(null != selectedAttribute && "checked".equalsIgnoreCase("checked")){
			isCashMode=true;
		}
		return isCashMode;
	}
	
	public void clickOnBuyCash(){
		clickOn(buyCashButton);
	}
	
}
