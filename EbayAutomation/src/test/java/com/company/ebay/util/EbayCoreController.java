package com.company.ebay.util;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.PointOption.point;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class EbayCoreController {
	protected static AndroidDriver driver;
	Dimension size;
	String filename;
	protected final int WAIT_TIME_OUT_IN_S=90;
	public AndroidDriver getdriver() throws IOException
	{
		driver=Initializer.creatint().getmobiledriver();
		return driver;
	}

//General Methods---------------------------------------------------------------------	
//Scrolling down-----------------------------------------------------------------------
	public void scrollDown() {
		size = driver.manage().window().getSize();
		System.out.println(size);
		int starty =size.height/2;
		int endy = (int) (size.height * 0.20);
		int startx = size.width / 2;
		System.out.println(endy);
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(point(startx, starty)).moveTo(point(startx, endy)).release().perform();
		System.out.println("Scrolled down");
     	}
//Scroll UP in the page-----------------------------------------------------------
	 	public void scrollUp() {
	 		sleep();
			size = driver.manage().window().getSize();
			System.out.println(size);
			int starty =size.height/2;
			int endy = (int) (size.height * 0.80);
			int startx = size.width / 2;
			TouchAction touchAction = new TouchAction(driver);
			touchAction.press(point(startx, starty)).moveTo(point(startx, endy)).release().perform();
	     	}
			
//Sleep for some time-----------------------------------------------------------------
		protected static void sleep() {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
//Wait for element---------------------------------------------------------------------
		public static void WaitForElement(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,300);
	    wait.until(ExpectedConditions.elementToBeClickable(ele));
	    
	}
	
//Scroll to element------------------------------------------------------------------	
	public void scrollTo(String text)
	{                
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	}

//Get Screen shot name--------------------------------------------------------------------
	
	private void setScreenShotFileName(String filename) {
		this.filename = filename;
	}

	private String getScreenShotFileName() {
		return filename;
	}
	
//==========ScreenShot=========================================	
	public String takeScreenShot() {
		String filename = null;
		System.out.println("Taking screen shot");
		WebDriver driver1 = new Augmenter().augment(driver);
		File file = ((TakesScreenshot) driver1)
				.getScreenshotAs(OutputType.FILE);
		String partwithoutdot = "";
		if (driver.currentActivity().contains("."))
			partwithoutdot = driver.currentActivity().substring(1,
					driver.currentActivity().length());
		try {
			filename = "./test-output/screenshot/" + "snap_" + partwithoutdot
					+ "_" + System.currentTimeMillis() + ".jpg";
			
			
			System.out.println(filename);
			setScreenShotFileName(filename);
			FileUtils.copyFile(file, new File(filename));
		} catch (IOException e) {
			System.out.println("Not able to save screen shot..." + e);
			e.printStackTrace();
		}
		return filename;
	}
//Time Stamp---------------------------------------------------------------------------
	
			protected String getTimeStamp() {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				return dateFormat.format(date);
			}	
			
//Method to check element presence ----------------------------------------------------			
			public boolean isElementPresentOnScreen(WebElement locator)

			{
			boolean isfround = false;
			if(locator.isDisplayed()) 
			{
				return true;
			}
			else
			{
				return false;
			}
			}

//Method to Back from the screen-------------------------------------------------------		
	public void backFromThisScreen() {
				driver.navigate().back();
			}
}
