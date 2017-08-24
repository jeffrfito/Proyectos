package pe.com.test.seleniumwd;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pe.com.test.seleniumwd.pom.CategoriaPage;
import pe.com.test.seleniumwd.pom.IniciarSesionPage;
import pe.com.test.seleniumwd.util.Utilitario;
import pe.com.test.util.excel.Excel;
import pe.com.test.util.mysql.MySql;

public class CategoriaWebDriverTest {

	private String urlInicial = "http://localhost:8080/TuBodeguitaWeb/";
	private CategoriaPage categoriaPage;
	private IniciarSesionPage iniciarSesionPage;
	
	private String rutaCarpetaError= "C:\\CapturasPantallas\\Categorias";
	
	
	@BeforeTest
	@Parameters({"navegador", "remoto"})
	public void inicioClase(String navegador, int remoto) throws Exception {
		iniciarSesionPage = new IniciarSesionPage(navegador, urlInicial, remoto == 1);
		categoriaPage = new CategoriaPage(navegador, iniciarSesionPage.getWebDriver());
	}
	
	@DataProvider(name = "datosEntrada")
	public static Object[][] datosPoblados(ITestContext context) {
		Object[][] datos = null;
		//TODO
		String fuenteDatos = 
			context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch(fuenteDatos){
		case "BD":
			datos = MySql.leerCategoriaMysql();
			break;
		case "Excel":
			String rutaArchivo = 
				context.getCurrentXmlTest().getParameter("rutaArchivo");
			datos = Excel.leerExcel(rutaArchivo);
			break;
		}
		return datos;
	}
	
	
	@Test(dataProvider = "datosEntrada")
	public void insertarCategoria(String nombre, String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion("admin", "clave");
			String valorObtenido = categoriaPage.insertar(nombre.trim());
			Assert.assertEquals(valorEsperado , valorObtenido);
		}
		catch(AssertionError e){
			Utilitario.caputarPantallarError(rutaCarpetaError, 
					e.getMessage(), categoriaPage.getWebDriver());
			Assert.fail(e.getMessage(), e);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		categoriaPage.cerrarPagina();
	}
	
}
