package com.techment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionsLibrary {
	// public static WebDriver driver = null;

	public static WebDriver launchBrowser(String browser) {
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invaild" + " " + "Browser Name");
		}
		driver.manage().window().maximize();
		 driver.manage()
         .timeouts()
         .implicitlyWait(Duration.ofSeconds(30));
		return driver;

	}

	public static void launchApp(String url, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
	}

	public static void passValue(WebDriver driver,WebElement element,String value  ) {
		//setColorByJS(element, driver);
		element.sendKeys(value);
	}

	public static void clickIt(WebElement element, WebDriver driver) {
		setColorByJS(element, driver);
		element.click();
	}

	public static void setAttribute(WebElement element, String text, WebDriver driver) {
		setColorByJS(element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);

	}

	public static void click(WebElement element, WebDriver driver) {
		//setColorByJS(element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);

	}
	
	public static String getText(WebElement element, WebDriver driver) {
		//setColorByJS(element, driver);
		return element.getText();

	}

	public static String getTextByJS(WebElement element, WebDriver driver) {
		setColorByJS(element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String text = js.executeScript("return documet.documentElement.innertext;").toString();
		return text;

	}

	public static void scrollByAxis(String text, String xvalue, String yvalue, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + xvalue + "," + yvalue + ")");

	}

	public static void scrollByElement(WebElement element, String value, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	public static void dropdownSelectByValue(WebElement element, String value, WebDriver driver) {
	//	setColorByJS(element, driver);
		Select s = new Select(element);
		s.selectByValue(value);

	}

	public static void switchWindow(WebDriver driver) {

		String parent = driver.getWindowHandle();
		Set<String> all = driver.getWindowHandles();

		for (String x : all) {
			if (!x.equals(parent)) {
				driver.switchTo().window(x);
			}
		}

	}

	public static void setColorByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background:blue;border:2px solid red;'):", element);

	}

	public static String screenShot(String filepath, WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(filepath));

		return filepath;
	}

	public static String getProperty(String key) throws Throwable, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(new File(System.getProperty("user.dir") + "\\Config\\System.property")));

		return p.getProperty(key);

	}

}
