/*
 ####Sigin Screen factory Object and its Methods####
 */
package com.company.ebay.screenobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.company.ebay.util.EbayCoreController;

public class LoginScreenFactory extends EbayCoreController {
	@FindBy(id="searchInput")
	public WebElement text;

	@FindBy(xpath="//*[@class='pure-button pure-button-primary-progressive']")
	public WebElement cl;

	@FindBy(id="com.ebay.mobile:id/button_sign_in")
	public WebElement btn_sigin;

//Click on Sign in Button
	public Object clickSiginBtn()
	{
	WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("com.ebay.mobile:id/button_sign_in")));	
	btn_sigin.click();
	sleep();
	return PageFactory.initElements(driver, HomeScreenFactory.class);
	
	}

}
