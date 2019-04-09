package com.mobile.automation.appium.selendroid.app.toastVerification;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Runner {

	public final static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
	public final static String BASE_URL = "http://google.com";

	public static AppiumDriver driver;
	public static String destDir;
	public static DateFormat dateFormat;
	public static String scrPath;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		File f = new File(
				System.getProperty("user.dir") + "//src//test//resources//apkFiles//selendroid-test-app-0.17.0.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.APP, f.getAbsolutePath());

		driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.findElement(MobileBy.AccessibilityId("buttonTestCD")).click();
		Thread.sleep(3000L);

		driver.findElement(MobileBy.id("android:id/button2")).click();
		
		takeScreenShot();
		
		String Text=OCR(scrPath);
		
		System.out.println(Text);
		
		Assert.assertTrue(Text.contains("Activity1 will continue"), "Activity will continue");

		driver.quit();

	}

	public static void takeScreenShot() {
		// Set folder name to store screenshots.
		destDir = "screenshots";
		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
			scrPath = System.getProperty("user.dir")+"\\"+destDir + "\\" + destFile;
			System.out.println("src path is "+scrPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String OCR(String ImagePath)
	{
		 String result = null;
		  File imageFile = new File(ImagePath);
	      ITesseract instance = new Tesseract();  
	      try {
	          result = instance.doOCR(imageFile);
	     
	      } catch (TesseractException e) {
	          System.err.println(e.getMessage());
	      }
		return result;
	  }

}
