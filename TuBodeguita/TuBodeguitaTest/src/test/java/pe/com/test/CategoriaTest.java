package pe.com.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import pe.com.core.business.CategoriaBusiness;
import pe.com.core.entity.Categoria;
import pe.com.test.bean.CategoriaBean;

public class CategoriaTest {
	
	private final CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
	private static Categoria categoria;
	
	@BeforeClass
	public void inicioClase() {
		System.out.println("**********************Inicio Clase CategoriaTest**********************");
	}

	@AfterClass
	public void finClase() {
		System.out.println("**********************Fin Clase CategoriaTest**********************");
	}

	@BeforeMethod
	public void inicioMetodo() {
		System.out.println("**********************Inicio Metodo CategoriaTest**********************");
	}

	@AfterMethod
	public void finMetodo() {
		System.out.println("Id Categoria: " + categoria.getIdCategoria());
		System.out.println("Nombre: " + categoria.getNombre());
		System.out.println("**********************Fin Metodo CategoriaTest**********************\n\n");
	}
	
	@DataProvider(name = "datosEntrada")
	public static Object[][] datosPoblados(){
		return new Object[][] {
			{new CategoriaBean("Categoria 01")},
			{new CategoriaBean("Categoria 02")},
			{new CategoriaBean("Categoria 03")}
		};
	}
	
	@Test(dataProvider = "datosEntrada")
	public void insertarDataProvider(CategoriaBean categoriaBean) {
		try{
			System.out.println("Metodo Insertar");
			//TODO
			categoria = new Categoria();
			
			categoria.setNombre(categoriaBean.getNombre());
			
			categoriaBusiness.insertar(categoria);
			Assert.assertTrue(categoria.getIdCategoria()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	@Test
	@Parameters({"nombreInsertar"})
	public void insertar(String nombreInsertar) {
		try{
			System.out.println("Metodo Insertar");
			//TODO
			categoria = new Categoria();
			categoria.setNombre(nombreInsertar);
			categoriaBusiness.insertar(categoria);
			Assert.assertTrue(categoria.getIdCategoria()>0);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods = {"insertar"})
	@Parameters({"nombreActualizar"})
	public void actualizar(String valorActualizar) {
		try{
			System.out.println("Metodo Actualizar");
			//TODO
			categoria.setNombre(valorActualizar);
			categoriaBusiness.actualizar(categoria);
			Assert.assertTrue(true);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods = {"actualizar"})
	public void obtener(){
		try{
			System.out.println("Metodo Obtener");
			//TODO
			Categoria categoriaBuscada = categoriaBusiness.obtener(categoria.getIdCategoria());
			Assert.assertEquals(categoria, categoriaBuscada);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dependsOnMethods = {"obtener"} , timeOut = 1000 )
	public void eliminar(){
		try{
			System.out.println("Metodo Eliminar");
			//TODO
			categoriaBusiness.eliminar(categoria);
			Assert.assertTrue(true);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(timeOut = 5000)
	public void timeOut(){
		try {
			//TODO
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
