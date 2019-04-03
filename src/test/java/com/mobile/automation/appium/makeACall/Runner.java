package com.mobile.automation.appium.makeACall;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class Runner {
	
	public final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	public final static String BASE_URL = "http://google.com";
	public final static String NODE_PATH = "C:\\Program Files\\nodejs\\node.exe";
	public final static String APPIUMJS_MAIN_PATH = "C:\\Users\\vinay\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
	public final static String SELENDROID_APK_LOCATION = "//src//test//resources//apkFiles//selendroid-test-app-0.17.0.apk";
	public final static String LOG_LOCATION = "//src//test//resources//logs//log.txt";
	
	public static AppiumDriver driver;
	
	@Test
	public void test1() throws MalformedURLException, InterruptedException {
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File(NODE_PATH)).
				withAppiumJS(new File(APPIUMJS_MAIN_PATH))
				.withLogFile(new File(System.getProperty("user.dir")+LOG_LOCATION))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
		
		service.start();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.dialer");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.dialer.extensions.GoogleDialtactsActivity");
		
		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);		
		Thread.sleep(3000L);
		
		driver.findElementByAccessibilityId("key pad").click();
		Thread.sleep(3000L);
		
		driver.findElementByAccessibilityId("1,").click();
		Thread.sleep(3000L);
		
		driver.findElementByAccessibilityId("5,JKL").click();
		Thread.sleep(3000L);
		
		driver.findElementByAccessibilityId("9,WXYZ").click();
		Thread.sleep(3000L);
		
		driver.findElementByAccessibilityId("dial").click();
		Thread.sleep(3000L);
		
		driver.quit();
		
		service.stop();
		
	}

}
