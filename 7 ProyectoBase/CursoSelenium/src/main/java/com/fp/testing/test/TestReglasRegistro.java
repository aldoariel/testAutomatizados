package com.fp.testing.test;
import static com.fp.testing.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.fp.testing.core.BaseTest;
import com.fp.testing.core.DSL;
import com.fp.testing.page.CampoEntrenamientoPage;

@RunWith(Parameterized.class)
public class TestReglasRegistro extends BaseTest {

	private DSL dsl;
	private CampoEntrenamientoPage page;
	
	@Parameter
	public String nombre;
	@Parameter(value=1)
	public String apellido;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] deportes;
	@Parameter(value=5)
	public String msg;
	

	@Before
	public void inicializa(){
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoEntrenamientoPage();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[]{}, "Nombre es obligatorio"},
			{"Juan", "", "", Arrays.asList(), new String[]{}, "Apellido es obligatorio"},
			{"Juan", "Perez", "", Arrays.asList(), new String[]{}, "Sexo es obligatorio"},
			{"Juan", "Perez", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Seguro que eres vegetariano?"},
			{"Juan", "Perez", "Masculino", Arrays.asList("Carne"), new String[]{"Karate", "Que es deporte?"}, "Haces deportes o no?"}
		});
	}
	
	@Test
	public void debeValidarReglas(){
		page.setNombre(nombre);
		page.setApellido(apellido);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} 
		if(sexo.equals("Femenino")) {
			page.setSexoFemenino();
		}
		if(comidas.contains("Carne")) page.setComidaCarne(); 
		if(comidas.contains("Pizza")) page.setComidaPizza(); 
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano(); 
		page.setDeporte(deportes);
		page.registrar();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObtenerTextoYAcepta());
	}
}
