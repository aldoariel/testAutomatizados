import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {

	@Test
	public void testeTextField(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");//carga el html
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escritura");//busca el elemento por ID y escribe por sendkeys
		
		Assert.assertEquals("Teste de escritura", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));//compara valor esperado versus valor evaluado
		
		driver.quit();
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\n\naasldjdlks\nUltima linea");
		
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linea", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		
		driver.quit();
	}
}
