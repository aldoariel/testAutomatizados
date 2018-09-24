package com.fp.testing.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestGoogle {
	
	private WebDriver driver;

	@Before
	public void inicializa(){
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
	}
	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void test() {

		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}

}
