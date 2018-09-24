package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fp.testing.core.DSL;
import com.fp.testing.core.DriverFactory;

public class TestAjax {
	
	private DSL dsl;

	@Before
	public void inicializa(){
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		DriverFactory.killDriver();
	}

	@Test
	public void testAjax(){
		dsl.escribir("j_idt85:name", "Teste");
		dsl.clicarBoton("j_idt85:j_idt88");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt85:display"), "Test"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt98")));
		Assert.assertEquals("Teste", dsl.obtenerTexto("j_idt85:display"));
	}
}
