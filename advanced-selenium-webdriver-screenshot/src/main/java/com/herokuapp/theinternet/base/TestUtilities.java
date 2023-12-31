package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

public class TestUtilities extends BaseTest {
	
	//Static Sleep
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="files")//we need to specify name here
	protected static Object[][] files() {
		return new Object[][] {
				{1, "logo.png" }, 
				{2, "logo1.jpg"}
				/*
				 * we have 2 files in files folder of src/main/resources so 2 arrays are
				 * mentioned here and we can specify anything inside arrays such as integer, string etc
				 */
		};
	}
	
	/** Take screenshot */
	protected void takeScreenshot(String fileName) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") 
				+ File.separator + "test-output" 
				+ File.separator + "screenshots"
				+ File.separator + getTodaysDate() 
				+ File.separator + testSuiteName 
				+ File.separator + testName
				+ File.separator + testMethodName 
				+ File.separator + getSystemTime() 
				+ " " + fileName + ".png";
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Todays date in yyyyMMdd format */
	private static String getTodaysDate() {
		return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}

	/** Current time in HHmmssSSS */
	private String getSystemTime() {
		return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
	}
}
