package pe.com.test.seleniumwd.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.pom.driver.TuBodeguitaDriver;

public class CategoriaPage {

	private By linkMntCategoria = By.xpath("//*[@id=\"frmMenu:j_idt18\"]/ul/li[2]/a");
	private By cajaNombre = By.id("txtNombre");
	private By botonNuevo = By.id("btnNuevo");
	private By botonGuardar = By.id("btnGuardar");
	private By mensajeRespuesta = By.id("messages");
	private WebDriver webDriver = null;
	
	public CategoriaPage(String navegador, boolean remoto) {
		webDriver = TuBodeguitaDriver.inicializarDriver(navegador, remoto);
	}
	
	public CategoriaPage(String navegador, WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public String insertar(String nombre) throws Exception {
		//TODO
		webDriver.findElement(linkMntCategoria).click();
		Thread.sleep(3000);
		webDriver.findElement(botonNuevo).click();
		Thread.sleep(3000);
		webDriver.findElement(cajaNombre).clear();
		webDriver.findElement(cajaNombre).sendKeys(nombre);
		webDriver.findElement(botonGuardar).click();
		Thread.sleep(3000);
		return webDriver.findElement(mensajeRespuesta).getText();
	}
	
	public void cerrarPagina(){
		//TODO
		TuBodeguitaDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
