package com.mobile.automation.appium.dragAndDrop.app.installation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Runner {
	
	public final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	public final static String BASE_URL = "http://google.com";
	
	public static AppiumDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		File f = new File(System.getProperty("user.dir")+"//src//test//resources//apkFiles//com.mobeta.android.demodslv.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.APP, f.getAbsolutePath());
		
		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000L);
		driver.quit();
		
	}

}
