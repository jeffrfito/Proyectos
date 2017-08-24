package pe.com.test.seleniumwd.pom.tatiendo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.pom.driver.TAtiendoDriver;
import pe.com.test.seleniumwd.pom.driver.TuBodeguitaDriver;

public class TAtiendoIniciarSesion {

	private By cajaUsuario = By.id("txtUsuario");
	private By cajaClave = By.id("txtClave");
	private By botonIniciarSesion = By.id("btnIniciarSesion");
	private String urlInicial;
	private WebDriver webDriver = null;
	
	public TAtiendoIniciarSesion(String navegador, String urlInicial, boolean remoto){
		webDriver = TuBodeguitaDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		webDriver.get(urlInicial);
		webDriver.findElement(cajaUsuario).clear();
		webDriver.findElement(cajaUsuario).sendKeys(usuario);
		webDriver.findElement(cajaClave).clear();
		webDriver.findElement(cajaClave).sendKeys(clave);
		webDriver.findElement(botonIniciarSesion).click();
		Thread.sleep(3000);
	}
	
	public void cerrarPagina(){
		TAtiendoDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
