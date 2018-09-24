package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fp.testing.core.DSL;
import com.fp.testing.core.DriverFactory;

public class TesteSincronismo {

	private DSL dsl;

	@Before
	public void inicializa(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException{
		dsl.clicarBoton("buttonDelay");
		Thread.sleep(5000);
		dsl.escribir("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException{
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBoton("buttonDelay");
		dsl.escribir("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	

	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException{
		dsl.clicarBoton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escribir("novoCampo", "Deu certo?");
	}
}
