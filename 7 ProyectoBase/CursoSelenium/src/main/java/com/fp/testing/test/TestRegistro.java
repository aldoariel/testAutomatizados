package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fp.testing.core.BaseTest;
import com.fp.testing.page.CampoEntrenamientoPage;

public class TestRegistro extends BaseTest {
	
	private CampoEntrenamientoPage page;

	@Before
	public void inicializa(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoEntrenamientoPage();
	}

	@Test
	public void deveRealizarRegistroConSucesso(){
		page.setNombre("Juan");
		page.setApellido("Perez");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridad("Maestrado");
		page.setDeporte("Natacion");
		page.registrar();
		
		Assert.assertEquals("Registrado!", page.obtenerResultadoRegistro());
		Assert.assertEquals("Juan", page.obtenerNombreRegistro());
		Assert.assertEquals("Perez", page.obtenerApellidoRegistro());
		Assert.assertEquals("Masculino", page.obtenerSexoRegistro());
		Assert.assertEquals("Pizza", page.obtenerComidaRegistro());
		Assert.assertEquals("Maestrado", page.obtenerEscolaridadRegistro());
		Assert.assertEquals("Natacion", page.obtenerDeportesRegistro());
	}
}
