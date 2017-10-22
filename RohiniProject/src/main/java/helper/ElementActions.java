package helper;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
	public WebDriver driver;
	public Actions actions;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	
	
	public ElementActions(WebDriver driver){
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver,20);
		js = (JavascriptExecutor)driver;
	}
	
	public void clickOn(By locator){
		clickOn(locator, false);
	}
	
	public void clickOn(By locator, boolean isScrollIntoView){
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		if(isScrollIntoView)
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
		driver.findElement(locator).click();
	}
	
	public void mouseHover(By locator){
		actions.moveToElement(driver.findElement(locator)).perform();
	}
	
	public void sendText(By locator, String text){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text+Keys.TAB);
		waitForPageToBeLoaded();
	}
	
	public String getAttribute(By locator, String attribute){
		return driver.findElement(locator).getAttribute(attribute);
	}
	
	
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);
	}
	
	public String getText(By locator){
		return getText(driver.findElement(locator));
	}
	
	public String getText(WebElement element){
		return element.getText();
	}
	
	public List<String> getMultipleText(By locator){
		List<WebElement> elements = driver.findElements(locator);
		List<String> textList = new ArrayList<String>();
		for(WebElement element: elements){
			textList.add(element.getText());
		}
		return textList;
	}
	
	public void openURL(String URL){
		driver.get(URL);
	}
	
	public void waitForPageToBeLoaded(){
		long intialTime = System.currentTimeMillis();
		long currentTime = intialTime;
		long maxWaitTime = 5*60*1000;
		
		while (!(js.executeScript("return document.readyState").equals("complete")) && currentTime-intialTime<maxWaitTime) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
			currentTime = System.currentTimeMillis();
		}
	}
	
	public void waitInSeconds(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
