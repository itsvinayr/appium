package com.mobile.automation.appium.swipe.contacts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Runner {

	public final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	public final static String BASE_URL = "http://google.com";

	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.contacts");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.contacts.activities.DialtactsActivity");

		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.findElement(MobileBy.id("com.android.contacts:id/action_bar_tab_contacts")).click();		
		Thread.sleep(3000L);
		
		WebElement parent = driver.findElement(MobileBy.id("android:id/list"));
		List<WebElement> list = parent.findElements(MobileBy.xpath("//*[@class='android.view.ViewGroup']"));
		int size = list.size();
		
		TouchAction action = new TouchAction(driver);
		action.press(new PointOption().withCoordinates(list.get(3).getLocation().getX(), list.get(3).getLocation().getY())).
				moveTo(new PointOption().withCoordinates(list.get(1).getLocation().getX(), list.get(1).getLocation().getY())).
				release().perform();
		Thread.sleep(3000L);

		driver.quit();

	}

}
