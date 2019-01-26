/*
 ####Home Screen and product Screen factory Object and its Methods####
 */
package com.company.ebay.screenobjects;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.company.ebay.util.EbayCoreController;

public class HomeScreenFactory extends EbayCoreController {
	@FindBy(id="com.ebay.mobile:id/edit_text_username")
	public WebElement txt_UserName;
	
	@FindBy(id="com.ebay.mobile:id/edit_text_password")
	public WebElement txt_Password;

	@FindBy(id="com.ebay.mobile:id/button_sign_in")
	public WebElement btn_Siginin;
	
	@FindBy(id="com.ebay.mobile:id/button_google_deny")
	public WebElement btn_NoThanks;
	
	@FindBy(id="com.ebay.mobile:id/search_box")
	public WebElement SearchBox;
	
	@FindBy(id="com.ebay.mobile:id/search_src_text")
	public WebElement SearchBox1;
	
	@FindBy(className="android.widget.RelativeLayout")
	public WebElement dispayeditems;
	
	@FindBy(id="com.ebay.mobile:id/textview_item_price")
	public WebElement itemprice;
	
	@FindBy(id="com.ebay.mobile:id/button_add_to_cart")
	public WebElement btn_AddtoCart;
	
	@FindBy(id="com.ebay.mobile:id/button_bin")
	public WebElement ByitNow;
	
	@FindBy(id="com.ebay.mobile:id/textview_header_title")
	public WebElement pdtsrntitle;
	
	@FindBy(id="com.ebay.mobile:id/textview_item_name")
	public WebElement prdname;
	
	@FindBy(id="com.ebay.mobile:id/estimated_delivery_textview")
	public WebElement prddelivery;
	
	@FindBy(id="com.ebay.mobile:id/textview_item_price")
	public WebElement prdprice;
	
	@FindBy(id="com.ebay.mobile:id/action_view_icon")
	public WebElement carticon;
	
	@FindBy(id="com.ebay.mobile:id/shopping_cart_checkout")
	public WebElement chekout;
	
	@FindBy(id="com.ebay.mobile:id/toolbar_centered_title")
	public WebElement chekoutTitle;
	
	@FindBy(id="com.ebay.mobile:id/item_title")
	public WebElement chekoutprodName;
	
	@FindBy(id="com.ebay.mobile:id/item_price")
	public WebElement chekoutprodPrice;
	
		
	//Methos to sigin in to the applicaton---------------------
	public void Sigin(String name,String password)
	{
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("com.ebay.mobile:id/edit_text_username")));	
		txt_UserName.clear();
		txt_UserName.sendKeys(name);
		txt_Password.sendKeys(password);
		btn_Siginin.click();
		
	}
//Method to select the no thanks button to link google account---------
	public void linknogoogleacccount()
	{
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(By
		        .id("com.ebay.mobile:id/button_google_deny")));
		btn_NoThanks.click();
	}
	
//Select product by  giving its name ------------------------------------------------------------- 	
	public void giveproduct(String pdtame)
	{
		SearchBox.click();
		SearchBox1.click();
		SearchBox1.sendKeys(pdtame);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		sleep();
	}

// get item price ----------------------------------------------------------------	
	public String itemprice(WebElement ele)
	{
	WaitForElement(itemprice);
	return itemprice.getText();	
	
	}
//Seelct item-----------------------------------------------------------------------    	
	public void selectanitem()
	{
		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		        .id("android.widget.RelativeLayout")));
		dispayeditems.click();
		
	}
//get the product name--------------------------------------------------------------	
	public  String getProdctname()
	{
		return prdname.getText();
	}

//get the product price--------------------------------------------------------------
	
	public String getProdcctprice()
	{
		return prdprice.getText();
	}

//get checkout product name--------------------------------------------------------------	
	public  String getcheckoutProdctname()
	{
		return chekoutprodName.getText();
	}
	
//get the product price--------------------------------------------------------------	
	public  String getcheckoutProdctprice()
	{
		return chekoutprodPrice.getText();
	}
//Wait for product screen to display-------------------------------------------------
	public void waitforproductScreen()
    {
    	WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		        .id("com.ebay.mobile:id/translucent_progress_bar")));
    }
//Add product to cart-----------------------------------------------------------------	
	public void addtoCartandOpen()
	{
		btn_AddtoCart.click();
		carticon.click();
		sleep();
	}
//Method to select checkout-----------------------------------------------------------	
	public void checkout()
	{
		
		chekout.click();
		sleep();
	}
	
}
