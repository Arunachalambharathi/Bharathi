package com.techment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FunctionsLibrary {
	static WebDriver driver = null;

	public static void lanchBrowser(String browser) {
		if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invaild" + " " + "Browser Name");
		}
		driver.manage().window().maximize();

	}

	public static void launchApp(String url) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
	}

	public static void passValue(String value, WebElement element) {
		element.sendKeys(value);
	}

	public static void clickIt(WebElement element) {
		element.click();
	}

	public static void setAttribute(WebElement element, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
	}

	public static void screenShot(File file) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, file);
	}

	public static String getProperty(String key) throws Throwable, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(new File(System.getProperty("user.dir") + "\\Config\\System.property")));

		return p.getProperty(key);

	}

}
