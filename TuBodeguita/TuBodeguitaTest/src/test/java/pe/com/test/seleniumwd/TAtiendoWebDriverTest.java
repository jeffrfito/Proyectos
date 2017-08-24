package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.pom.tatiendo.TAtiendoIniciarSesion;
import pe.com.test.seleniumwd.pom.tatiendo.TAtiendoRegistrarCliente;
import pe.com.test.seleniumwd.pom.tatiendo.TAtiendoRegistrarPedido;
import pe.com.test.seleniumwd.util.Utilitario;
import pe.com.test.util.excel.Excel;

public class TAtiendoWebDriverTest {

	private String urlInicial = "http://localhost:8080/TAtiendoWeb/";
	private TAtiendoRegistrarCliente atiendoRegistrarCliente;
	private TAtiendoIniciarSesion atiendoIniciarSesion;
	private TAtiendoRegistrarPedido atiendoRegistrarPedido;

	private String rutaCarpetaError = "C:\\CapturasPantallas\\Categorias";

	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) throws Exception {
		atiendoRegistrarCliente = new TAtiendoRegistrarCliente(navegador, urlInicial, remoto == 1);
		atiendoIniciarSesion = new TAtiendoIniciarSesion(navegador, urlInicial, remoto == 1);
		atiendoRegistrarPedido = new TAtiendoRegistrarPedido(atiendoIniciarSesion.getWebDriver());
	}

	@DataProvider(name = "datosUsuarios")
	public static Object[][] datosUsuarios(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch (fuenteDatos) {
		case "BD":
			datos = null;
			break;
		case "Excel":
			String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoUsuarios");
			datos = Excel.leerExcel(rutaArchivo);
			break;
		}
		return datos;
	}

	@DataProvider(name = "datosPedidos")
	public static Object[][] datosPedidos(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch (fuenteDatos) {
		case "BD":
			datos = null;
			break;
		case "Excel":
			String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoPedidos");
			datos = Excel.leerExcel(rutaArchivo);
			break;
		}
		return datos;
	}

	@Test(dataProvider = "datosUsuarios")
	public void insertarUsuarios(String nombre, String apellido, String correo, String clave, String valorEsperado)
			throws Exception {
		try {
			String valorObtenido = atiendoRegistrarCliente.registrarCliente(nombre, apellido, correo, clave);
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch (AssertionError e) {
			String mensaje = "";
			mensaje += "Mensaje de Error: " + e.getMessage();
			Utilitario.caputarPantallarError(rutaCarpetaError, mensaje, atiendoRegistrarCliente.getWebDriver());
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(dataProvider = "datosPedidos", dependsOnMethods = { "insertarUsuarios" })
	public void insertarPedido(String correo, String clave, String producto, String cantidad, String valorEsperado)
			throws Exception {
		try {
			atiendoIniciarSesion.iniciarSesion(correo, clave);
			String valorObtenido = atiendoRegistrarPedido.registrarPedido(correo, clave, producto, cantidad);
			Assert.assertTrue(valorObtenido.startsWith(valorEsperado));
		} catch (AssertionError e) {
			String mensaje = "";
			mensaje += "Mensaje de Error: " + e.getMessage();
			Utilitario.caputarPantallarError(rutaCarpetaError, mensaje, atiendoRegistrarPedido.getWebDriver());
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterClass
	public void finClase() throws Exception {
		atiendoRegistrarCliente.cerrarPagina();
		atiendoRegistrarPedido.cerrarPagina();
	}
}
