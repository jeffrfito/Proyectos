package pe.com.test.seleniumwd.pom.tatiendo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.pom.driver.TAtiendoDriver;
import pe.com.test.seleniumwd.pom.driver.TuBodeguitaDriver;

public class TAtiendoRegistrarPedido {
	
	private By linkRegistrarPedido = By.xpath("/html/body/table/tbody/tr[2]/td[1]/form/div/ul/li[2]/a/span");
	private By cajaCantidadPedido = By.id("spCantidad_input");
	private By comboProductoPedido = By.id("cboProducto_label");
	private By botonMasPedido = By.xpath("/html/body/table/tbody/tr[2]/td[2]/form/div/div[2]/table[1]/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/button");
	private By botonGuardarPedido = By.xpath("/html/body/table/tbody/tr[2]/td[2]/form/div/div[2]/table[3]/tbody/tr/td/button");
	private By mensajeRespuestaPedido = By.id("messages");
	private WebDriver webDriver = null;
	
	public TAtiendoRegistrarPedido(String navegador, boolean remoto) {
		webDriver = TuBodeguitaDriver.inicializarDriver(navegador, remoto);
	}
	
	public TAtiendoRegistrarPedido(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	private By obtenerProducto(String nombre){
		return By.xpath("/html/body/div[6]/div/ul/li[text()='"+ nombre + "']");
	}

	public String registrarPedido(String correo, String clave, String producto, String cantidad) throws Exception{
		Thread.sleep(2000);
		webDriver.findElement(linkRegistrarPedido).click();
		Thread.sleep(2000);
		webDriver.findElement(comboProductoPedido).click();
		Thread.sleep(1000);
		webDriver.findElement(obtenerProducto(producto)).click();
		Thread.sleep(1000);
		webDriver.findElement(cajaCantidadPedido).clear();
		webDriver.findElement(cajaCantidadPedido).sendKeys(cantidad);
		webDriver.findElement(botonMasPedido).click();
		Thread.sleep(2000);
		webDriver.findElement(botonGuardarPedido).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuestaPedido).getText();
		
	}
	
	public void cerrarPagina(){
		//TODO
		TAtiendoDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
