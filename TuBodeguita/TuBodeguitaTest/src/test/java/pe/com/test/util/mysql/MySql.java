package pe.com.test.util.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySql {
	private static Connection cn = null;
	private static PreparedStatement pr = null;
	private static ResultSet rs = null;

	

	public static String[][] leerCategoriaMysql() {
		String[][]  datos = null;
		int i = 0;
		try {
			//TODO
			cn = obtenerConexion();
			pr = cn.prepareStatement("SELECT nombre, valorEsperado FROM categoriafuentedatos");
			rs = pr.executeQuery();
			datos = new String[numeroFilas(rs)][2];
			while(rs.next()){
				datos[i][0] = rs.getString("nombre");
				datos[i][1] = rs.getString("valorEsperado");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				pr.close();
			} catch (Exception e) {
			}
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return datos;
	}
	
	private static Connection obtenerConexion() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return (DriverManager.getConnection("jdbc:mysql://localhost:3306/bdproductotest", "root", ""));
	}
	
	private static int numeroFilas(ResultSet resultSet){
	    int totalFilas = 0;
	    try {
	        resultSet.last();
	        totalFilas = resultSet.getRow();
	        resultSet.beforeFirst();
	    } 
	    catch(Exception ex)  {
	        return 0;
	    }
	    return totalFilas ;    
	}
}
