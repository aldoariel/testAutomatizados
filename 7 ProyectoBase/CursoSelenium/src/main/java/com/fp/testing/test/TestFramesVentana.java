
package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.fp.testing.core.DSL;
import com.fp.testing.core.DriverFactory;


public class TestFramesVentana {
	
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
	public void debeInteractuarConFrames(){
		dsl.entrarFrame("frame1");
		dsl.clicarBoton("frameButton");
		String msg = dsl.alertaObtenerTextoYAcepta();
		Assert.assertEquals("Frame OK!", msg);

		dsl.salirFrame();
		dsl.escribir("elementosForm:nombre", msg);
	}
	
	@Test
	public void debeInteractuarConFrameEscondido(){
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBoton("frameButton");
		String msg = dsl.alertaObtenerTextoYAcepta();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void debeInteractuarConVentanas(){
		dsl.clicarBoton("buttonPopUpEasy");
		dsl.cambiarVentana("Popup");
		dsl.escribir(By.tagName("textarea"), "Funciono?");
		getDriver().close();
		dsl.cambiarVentana("");
		dsl.escribir(By.tagName("textarea"), "que paso?");
	}
	
	@Test
	public void debeInteractuarConVentanaSinTitulo(){
		dsl.clicarBoton("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.cambiarVentana((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escribir(By.tagName("textarea"), "Funciono?");
		dsl.cambiarVentana((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escribir(By.tagName("textarea"), "que paso??");
	}
}
