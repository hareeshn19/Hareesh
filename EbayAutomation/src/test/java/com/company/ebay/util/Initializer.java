package com.company.ebay.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Initializer extends EbayCoreController {
	
	private Initializer()
	{
		
	}

	public static Initializer initi=null;
//Set the driver instance-------------------------------------------------------------	
	public static Initializer creatint()
	{
		if(initi==null)
		{
			initi=new Initializer();
		}
		return initi;
					
	}
	
//Create the driver-------------------------------------------------------------------

public AndroidDriver getmobiledriver() throws IOException
{
	URL HUB_URL = null;
	   String APPIUM_IP="127.0.0.1";
		String APPIUM_PORT="4723";
		HUB_URL = new URL("http://" + APPIUM_IP + ":" + APPIUM_PORT
					+ "/wd/hub");
			System.out.println("The link"+ HUB_URL);	
			Properties ivProp = prop();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//After setting apk path in Config.properties enable the below code
		//File app = new File(ivProp.getProperty("APK_PATH"));
		//capabilities.setCapability("app",ivProp.getProperty("APK_PATH"));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
			ivProp.getProperty("PLATFORM"));
		capabilities.setCapability(	MobileCapabilityType.PLATFORM_VERSION,
				ivProp.getProperty("PLATFORM_VERSION"));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				ivProp.getProperty("DEVICE_NAME"));
		capabilities.setCapability("appPackage",ivProp.getProperty("APPPACKAGE"));
		capabilities.setCapability("appActivity",ivProp.getProperty("APPACTIVITY"));
		driver =new AndroidDriver(HUB_URL,capabilities);
		
		return driver;
}

//Property file --------------------------------------------------------------------
public Properties prop() throws IOException
{
Properties p=new Properties();
File f =new File(System.getProperty("user.dir")+"/Config/config.properties");
FileInputStream fs=new FileInputStream(f);
p.load(fs);
return p;
}

}
