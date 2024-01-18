package com.techment.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.techment.pages.LoginPage;
import com.techment.utility.FunctionsLibrary;

public class Test1 extends FunctionsLibrary {

	ExtentReports e = new ExtentReports(System.getProperty("user.dir") + "\\Report\\report.html");
	ExtentTest createTest = null;

	LoginPage l = null;

	private static final ThreadLocal<WebDriver> threadLocalDriver = ThreadLocal.withInitial(() -> {
	    return null; //Your supplier goes here.
	});
	@BeforeMethod
	public void Setup() throws IOException, Throwable {
		WebDriver driver = launchBrowser(getProperty("browser"));
		// set driver
		threadLocalDriver.set(driver);

		System.out.println("Before Method Thread ID: " + Thread.currentThread().getId());

		// get URL
		launchApp(getProperty("url"), getDriver());
		
		
	}

	// get thread-safe driver
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	@AfterMethod
	public void tearDown(ITestResult res) throws Throwable {

		if (res.getStatus() == ITestResult.SUCCESS) {
			createTest.log(LogStatus.PASS, res.getName() + " passed");
		} else if (res.getStatus() == ITestResult.FAILURE) {
			createTest.log(LogStatus.FAIL, res.getName() + " failed");
			createTest.log(LogStatus.FAIL,
					createTest.addScreenCapture(
							screenShot("D:\\Bharathi\\TechmentProject\\Screenshot\\" + res.getName() + ".png",getDriver()))
					);
		}

		getDriver().quit();

		System.out.println("After Method Thread ID: " + Thread.currentThread().getId());

		threadLocalDriver.remove();
		
	}

	

	@Test
	public void tc1() {
		createTest = e.startTest("Test case 1");
		createTest.log(LogStatus.PASS, "Browser launched successfully");
		createTest.log(LogStatus.PASS, "Application launched successfully");

		l = new LoginPage(getDriver());
		String data1 = "Learn SQL in Practical + Database Testing from Scratch";

		getDriver().findElement(By.xpath("//td[text()='" + data1 + "']/following-sibling::td"));
		Assert.assertEquals(l.getData1().getText(), "25");

	}

	

	@Test
	public void tc2() throws IOException {
		createTest = e.startTest("Test case 2");

		createTest.log(LogStatus.PASS, "Browser launched successfully");
		createTest.log(LogStatus.PASS, "Application launched successfully");
		String data2 = "Learn JMETER from Scratch - (Performance + Load) Testing Tool";
		getDriver().findElement(By.xpath("//td[text()='" + data2 + "']/following-sibling::td"));
		Assert.assertEquals(l.getData2().getText(), "30");
		// createTest.log(LogStatus.FAIL,createTest.addScreenCapture(screenShot("D:\\Bharathi\\TechmentProject\\Screenshot\\"+res+".png")),
		// "Test method 1 passed");

	}
	
	@Test
	public void tc3() throws IOException {
		createTest = e.startTest("Test case 3");

		createTest.log(LogStatus.PASS, "Browser launched successfully");
		createTest.log(LogStatus.PASS, "Application launched successfully");
		LoginPage l=new LoginPage(getDriver());
		
		click(l.getOpentab(), getDriver());
		createTest.log(LogStatus.PASS, "Open Tab successfully");
		switchWindow(getDriver());
		createTest.log(LogStatus.PASS, "window switched successfully");
		String text = getText(l.getNewtabdata(), getDriver());
		Assert.assertEquals(text, "Access all our Courses");
		createTest.log(LogStatus.PASS, "Expected and Actual texts are matching");

	}
	@Test
	public void tc4() 	{
		createTest = e.startTest("Test case 4");
		createTest.log(LogStatus.PASS, "Browser launched successfully");
		createTest.log(LogStatus.PASS, "Application launched successfully");
		LoginPage l=new LoginPage(getDriver());
		
		dropdownSelectByValue(l.getDropdown(), "option2", getDriver());
		createTest.log(LogStatus.PASS, "dropdown selected successfully");
	}

	@AfterClass
	public void afterclass() {
		e.endTest(createTest);
		e.flush();
	}
	

}
