package com.techment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.techment.utility.FunctionsLibrary;

public class LoginPage extends FunctionsLibrary {

	@FindBy(xpath = "//td[text()='Learn SQL in Practical + Database Testing from Scratch']/following-sibling::td")
	private WebElement data1;

	@FindBy(xpath = "//td[text()='Learn JMETER from Scratch - (Performance + Load) Testing Tool']/following-sibling::td")
	private WebElement data2;
	@FindBy(xpath = "//a[@id='opentab']")
	private WebElement opentab;
	@FindBy(xpath = "//a[text()='Access all our Courses']")
	private WebElement newtabdata;
	@FindBy(id = "dropdown-class-example")
	private WebElement dropdown;

	public WebElement getData1() {
		return data1;
	}

	public WebElement getData2() {
		return data2;
	}

	public WebElement getOpentab() {
		return opentab;
	}

	public WebElement getNewtabdata() {
		return newtabdata;
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public LoginPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

}
