package pe.com.test.seleniumwd.pom.tatiendo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pe.com.test.seleniumwd.pom.driver.TAtiendoDriver;
import pe.com.test.seleniumwd.pom.driver.TuBodeguitaDriver;

public class TAtiendoRegistrarCliente {

	private By linkRegistrarCliente = By.xpath("/html/body/form/div[2]/div/table/tbody/tr[4]/td[2]/a");
	private By cajaNombreCliente = By.id("txtNombre");
	private By cajaApellidoCliente = By.id("txtApellido");
	private By cajaCorreoCliente = By.id("txtCorreo");
	private By cajaUsuarioCliente = By.id("txtClave");
	private By botonGuardarCliente= By.id("btnGuardar");
	private By mensajeRespuestaCliente = By.id("messages");
	private WebDriver webDriver = null;
	private String urlInicial;
	
	public TAtiendoRegistrarCliente(String navegador, String urlInicial, boolean remoto) {
		webDriver = TuBodeguitaDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public TAtiendoRegistrarCliente(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public String registrarCliente(String nombre, String apellido, String correo, String clave) throws Exception{
		webDriver.get(urlInicial);
		webDriver.findElement(linkRegistrarCliente).click();
		Thread.sleep(2000);
		webDriver.findElement(cajaNombreCliente).clear();
		webDriver.findElement(cajaNombreCliente).sendKeys(nombre);
		webDriver.findElement(cajaApellidoCliente).clear();
		webDriver.findElement(cajaApellidoCliente).sendKeys(apellido);
		webDriver.findElement(cajaCorreoCliente).clear();
		webDriver.findElement(cajaCorreoCliente).sendKeys(correo);
		webDriver.findElement(cajaUsuarioCliente).clear();
		webDriver.findElement(cajaUsuarioCliente).sendKeys(clave);
		webDriver.findElement(botonGuardarCliente).click();
		Thread.sleep(2000);
		return webDriver.findElement(mensajeRespuestaCliente).getText();
	}
	
	public void cerrarPagina(){
		TAtiendoDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
