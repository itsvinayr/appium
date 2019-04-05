package com.mobile.automation.appium.dragAndDrop.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.Response;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Runner {
	
	public final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	public final static String BASE_URL = "http://google.com";
	
	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.mobeta.android.demodslv");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.mobeta.android.demodslv.Launcher");
		
		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(3000L);
		
		driver.findElement(MobileBy.xpath(".//*[@text='Basic usage playground']")).click();
		Thread.sleep(3000L);
		
		WebElement parent = driver.findElement(MobileBy.id("android:id/list"));
		List<WebElement> child = parent.findElements(MobileBy.className("android.view.ViewGroup"));
		System.out.println("Number of elements "+child.size());
		
		WebElement source = child.get(0).findElement(MobileBy.id("com.mobeta.android.demodslv:id/drag_handle"));
		WebElement destination = child.get(2).findElement(MobileBy.id("com.mobeta.android.demodslv:id/drag_handle"));
		
		driver.quit();
		
	}

}
