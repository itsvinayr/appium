package com.mobile.automation.appium.androidHardwareKeyEvents;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
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
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
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
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		
		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
		Thread.sleep(3000L);
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000L);
		
		// Using keystrokes to type
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.A));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.P));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.P));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.I));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.U));
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.M));		
		Thread.sleep(3000L);
		
		// using normal driver to type
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).clear();
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("majjiga");
		Thread.sleep(3000L);
		
		// using actions class to type, make sure to add 
		Actions action = new Actions(driver);
		action.sendKeys("helloworld!!!").perform();
		Thread.sleep(3000L);
		
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.HOME));
		Thread.sleep(3000L);
		((PressesKey) driver).longPressKey(new KeyEvent(AndroidKey.HOME));
		Thread.sleep(3000L);
		driver.quit();
		
		service.stop();
		
	}

}
