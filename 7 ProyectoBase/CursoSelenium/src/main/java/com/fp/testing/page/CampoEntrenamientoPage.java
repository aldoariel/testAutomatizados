package com.fp.testing.page;
import org.openqa.selenium.By;

import com.fp.testing.core.BasePage;

public class CampoEntrenamientoPage extends BasePage {

	public void setNombre(String nombre) {
		dsl.escribir("elementosForm:nombre", nombre);
	}
	
	public void setApellido(String apellido) {
		dsl.escribir("elementosForm:apellido", apellido);
	}
	
	public void setSexoMasculino(){
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFemenino(){
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza(){
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridad(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridad", valor);
	}
	
	public void setDeporte(String... valores) {
		for(String valor: valores)
			dsl.selecionarCombo("elementosForm:deportes", valor);
	}
	
	public void registrar(){
		dsl.clicarBoton("elementosForm:registrar");
	}
	
	public String obtenerResultadoRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	
	public String obtenerNombreRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descNombre']/span"));
	}
	
	public String obtenerApellidoRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descApellido']/span"));
	}
	
	public String obtenerSexoRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obtenerComidaRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obtenerEscolaridadRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descEscolaridad']/span"));
	}
	
	public String obtenerDeportesRegistro(){
		return dsl.obtenerTexto(By.xpath("//*[@id='descDeportes']/span"));
	}
}
