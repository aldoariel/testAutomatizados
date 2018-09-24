package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fp.testing.core.DSL;
import com.fp.testing.core.DriverFactory;

public class TestAlert {
	
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
	public void debeInteractuarConAlertSimples(){
		dsl.clicarBoton("alert");
		String texto = dsl.alertaObtenerTextoYAcepta(); 
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.escribir("elementosForm:nombre", texto);
	}
	
	@Test
	public void debeInteractuarConAlertConfirm(){
		dsl.clicarBoton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObtenerTextoYAcepta());
		Assert.assertEquals("Confirmado", dsl.alertaObtenerTextoYAcepta());
		
		dsl.clicarBoton("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObtenerTextoYNiega());
		Assert.assertEquals("Negado", dsl.alertaObtenerTextoYNiega());
	}
	
	@Test
	public void debeInteractuarConAlertPrompt(){
		dsl.clicarBoton("prompt");
		Assert.assertEquals("Digite un numero", dsl.alertaObterTexto());
		dsl.alertaEscribir("12");
		Assert.assertEquals("Era 12?", dsl.alertaObtenerTextoYAcepta());
		Assert.assertEquals(":D", dsl.alertaObtenerTextoYAcepta());
	}
}
