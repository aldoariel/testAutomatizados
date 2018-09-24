package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.fp.testing.core.DSL;
import com.fp.testing.core.DriverFactory;

public class TesteCampoEntrenamiento {
	
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
	public void testTextField(){
		dsl.escribir("elementosForm:nombre", "Test de escritura");
		Assert.assertEquals("Test de escritura", dsl.obtenerValorCampo("elementosForm:nombre"));
	}
	
	@Test
	public void testTextFieldDoble(){
		dsl.escribir("elementosForm:nombre", "Juan");
		Assert.assertEquals("Juan", dsl.obtenerValorCampo("elementosForm:nombre"));
		dsl.escribir("elementosForm:nombre", "Perez");
		Assert.assertEquals("Perez", dsl.obtenerValorCampo("elementosForm:nombre"));
	}
	
	@Test
	public void debeInteractuarConTextArea(){
		dsl.escribir("elementosForm:sugerencias", "test\n\naasldjdlks\nUltima linea");
		Assert.assertEquals("test\n\naasldjdlks\nUltima linea", dsl.obtenerValorCampo("elementosForm:sugerencias"));
	}
	
	@Test
	public void debeInteractuarConRadioButton(){
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void debeInteractuarConCheckbox(){
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void debeInteractuarConCombo(){
		dsl.selecionarCombo("elementosForm:escolaridad", "Secundario completo");
		Assert.assertEquals("Secundario completo", dsl.obtenerValorCombo("elementosForm:escolaridad"));
	}
	
	@Test
	public void debeVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obtenerCantidadOpcionesCombo("elementosForm:escolaridad"));
		Assert.assertTrue(dsl.verificarOpcionCombo("elementosForm:escolaridad", "Maestrado"));
	}
	
	@Test
	public void debeVerificarValoresComboMultiple(){
		dsl.selecionarCombo("elementosForm:deportes", "Natacion");
		dsl.selecionarCombo("elementosForm:deportes", "Carrera");
		dsl.selecionarCombo("elementosForm:deportes", "Que es deporte?");

		List<String> opcionesMarcadas = dsl.optenerValoresCombo("elementosForm:deportes");
		Assert.assertEquals(3, opcionesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:deportes", "Carrera");
		opcionesMarcadas = dsl.optenerValoresCombo("elementosForm:deportes");
		Assert.assertEquals(2, opcionesMarcadas.size());
		Assert.assertTrue(opcionesMarcadas.containsAll(Arrays.asList("Natacion", "Que es deporte?")));
	}
	
	@Test
	public void debeInteractuarConBotones(){
		dsl.clicarBoton("buttonSimple");
		Assert.assertEquals("Gracias!", dsl.obtenerValueElemento("buttonSimple"));
	}
	
	@Test
	public void debeInteractuarLinks(){
		dsl.clicarLink("Volver");
		
		Assert.assertEquals("Volvio!", dsl.obtenerTexto("resultado"));
	}
	
	@Test
	public void debeBuscarTextosEnPagina(){
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Entrenamiento"));
		Assert.assertEquals("Campo de Entrenamiento", dsl.obtenerTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado donde clica, muchas trampas...", 
				dsl.obtenerTexto(By.className("facilEncontrar")));
	}
	
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Test js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nombre').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:apellido').type = 'radio'");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nombre"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void debeClicarBotonTabla(){
		dsl.clicarBotonTabla("Escolaridad", "Maestrado", "Radio", "elementosForm:tableUsuarios");
	}
	
}


