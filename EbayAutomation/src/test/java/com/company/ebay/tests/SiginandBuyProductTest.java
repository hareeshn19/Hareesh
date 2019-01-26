package com.company.ebay.tests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.company.ebay.screenobjects.HomeScreenFactory;
import com.company.ebay.screenobjects.LoginScreenFactory;
import com.company.ebay.util.EbayCoreController;
import com.company.ebay.util.XlsTestDataReader;

public class SiginandBuyProductTest extends EbayCoreController {
	
	AndroidDriver driver;
	LoginScreenFactory login;
	HomeScreenFactory home;
	private XlsTestDataReader testdataLoader =new XlsTestDataReader() ;
	String producname;
    String productprice;
	
//Creating the Driver-----------------------------------------------------------
	
@BeforeClass
	 public void opendriver() throws IOException
	 {
		driver=getdriver();
		login=PageFactory.initElements(driver, LoginScreenFactory.class);
	 }	

//Test Methods of scenarios------------------------------------------------------

// SiginIn to application========================================================

@Test(dataProvider="dpsignin")
public void appsignin(String name,String password)
{
	 Object obj=login.clickSiginBtn();
	 home=(HomeScreenFactory)obj;
	 home.Sigin(name, password);
	 home.linknogoogleacccount();
	 Assert.assertEquals(driver.currentActivity(),".activities.MainActivity");

}


// Select the product from using search option-----------------------------------------

@Test(dataProvider="dpsignin" ,dependsOnMethods="appsignin")
public void selectproduct(String product)
{
	home.giveproduct(product);   
	Assert.assertTrue(isElementPresentOnScreen(home.pdtsrntitle));
    scrollDown();
    home.selectanitem();
    home.waitforproductScreen();
    producname=home.getProdctname();
    productprice=home.getProdcctprice();
    try
    {
    scrollTo("ADD TO CART");
    }
    catch(Exception e)
    {
    	System.out.println("The product can't add to cart go back and select");
    	backFromThisScreen();
    	scrollUp();
    	home.selectanitem();
        home.waitforproductScreen();
        producname=home.getProdctname();
        productprice=home.getProdcctprice();
    	    	
    }
   Assert.assertTrue(isElementPresentOnScreen(home.btn_AddtoCart));
}

// Test to Buy product from selected list and -----------------------------------------
//verify the checkout product is the same selected before----------------------------------------------   
@Test(dependsOnMethods="selectproduct")
public void cartandbuyproduct()
{
	System.out.println(producname);
	home.addtoCartandOpen();
	home.checkout();
	Assert.assertTrue(isElementPresentOnScreen(home.chekoutTitle));
	Assert.assertEquals(home.getcheckoutProdctname(),producname);
	Assert.assertEquals(home.getcheckoutProdctprice(),productprice);
	
}

@AfterClass
public void close()
{
	//driver.quit();
}


//Test Method to read  test data from excel file------------------------------------ 
@DataProvider(name="dpsignin")
public Object getdata(Method m) throws BiffException, IOException
{
	String[][] dt=testdataLoader.ReadTable(System.getProperty("user.dir") + "/Resource/TestData.xls","login", m.getName());
	
	return dt;
	
}


}
 


