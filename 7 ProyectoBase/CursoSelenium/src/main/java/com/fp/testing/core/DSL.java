package com.fp.testing.core;
import static com.fp.testing.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	/********* TextField y TextArea ************/
	
	public void escribir(By by, String texto){
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public void escribir(String id_campo, String texto){
		escribir(By.id(id_campo), texto);
	}
	
	public String obtenerValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/********* Radio  Check ************/
	
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/********* Combo ************/
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obtenerValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> optenerValoresCombo(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:deportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcion: allSelectedOptions) {
			valores.add(opcion.getText());
		}
		return valores;
	}
	
	public int obtenerCantidadOpcionesCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcionCombo(String id, String opcion){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcion)){
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}
	
	/********* Boton ************/
	
	public void clicarBoton(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obtenerValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/********* Link ************/
	
	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}
	
	/********* Textos ************/
	
	public String obtenerTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obtenerTexto(String id) {
		return obtenerTexto(By.id(id));
	}
	
	/********* Alerts ************/
	
	public String alertaObterTexto(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObtenerTextoYAcepta(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObtenerTextoYNiega(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	public void alertaEscribir(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/********* Frames y Ventanas ************/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void salirFrame(){
		getDriver().switchTo().defaultContent();
	}
	
	public void cambiarVentana(String id) {
		getDriver().switchTo().window(id);
	}
	
	/************** JS *********************/
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	/************** Tabla *********************/
	
	public void clicarBotonTabla(String columnaBusca, String valor, String columnaBoton, String idTabla){
		//buscar columna del registro
		WebElement tabla = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColumna = obtenerIndiceColumna(columnaBusca, tabla);
		
		//encontrar linea del registro
		
		int idLinea = obtenerIndiceLinea(valor, tabla, idColumna);
		
		//buscar columna del botom
		int idColumnaBoton = obtenerIndiceColumna(columnaBoton, tabla);
		
		//clicar el boton de la  celda encontrada
		WebElement celda = tabla.findElement(By.xpath(".//tr["+idLinea+"]/td["+idColumnaBoton+"]"));
		celda.findElement(By.xpath(".//input")).click();
		
	}

	protected int obtenerIndiceLinea(String valor, WebElement tabla, int idColumna) {
		List<WebElement> lineas = tabla.findElements(By.xpath("./tbody/tr/td["+idColumna+"]"));
		int idLinea = -1;
		for(int i = 0; i < lineas.size(); i++) {
			if(lineas.get(i).getText().equals(valor)) {
				idLinea = i+1;
				break;
			}
		}
		return idLinea;
	}

	protected int obtenerIndiceColumna(String columna, WebElement tabla) {
		List<WebElement> columnas = tabla.findElements(By.xpath(".//th"));
		int idColumna = -1;
		for(int i = 0; i < columnas.size(); i++) {
			if(columnas.get(i).getText().equals(columna)) {
				idColumna = i+1;
				break;
			}
		}
		return idColumna;
	}
}
