package com.mobile.automation.appium.locatorStrategies;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
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
		
		File f = new File(System.getProperty("user.dir")+SELENDROID_APK_LOCATION);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
		
		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		// Clicking on Display text view button in different ways
		
		// 1. with resource id --> Note: Use MobileBy
		driver.findElement(MobileBy.id("io.selendroid.testapp:id/visibleButtonTest")).click();
		Assert.assertTrue(true, "identified by resoure id");
		
		Thread.sleep(3000L);
		
		// 2. using accessibility id --> which is content desc
		driver.findElement(MobileBy.AccessibilityId("visibleButtonTestCD")).click();
		Assert.assertTrue(true, "identified by accessibility id");
		
		Thread.sleep(3000L);
		
		// 3. using xpath
		driver.findElement(MobileBy.xpath(".//*[@content-desc='visibleButtonTestCD']")).click();
		Assert.assertTrue(true, "identified by xpath");
		
		Thread.sleep(3000L);
		
		// 3. using xpath contains
		driver.findElement(MobileBy.xpath(".//*[contains(@text,'Display text view')]")).click();
		Assert.assertTrue(true, "identified by xpath contains");
		
		Thread.sleep(3000L);
		
		// 3. using xpath contains with tagname which is generally class name
		driver.findElement(MobileBy.xpath(".//android.widget.Button[contains(@text,'Display text view')]")).click();
		Assert.assertTrue(true, "identified by tagname");
		
		Thread.sleep(3000L);
		
		// 3. using xpath contains with tagname which is generally class name type 2
		driver.findElement(MobileBy.xpath(".//android.widget.LinearLayout/android.widget.Button[contains(@text,'Display text view')]")).click();
		Assert.assertTrue(true, "identified by tagname parent");
		
		Thread.sleep(3000L);
		
		driver.quit();
		
		service.stop();
		
	}

}
