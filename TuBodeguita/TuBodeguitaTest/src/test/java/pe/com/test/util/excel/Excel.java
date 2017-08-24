package pe.com.test.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Excel {

	
	public static String[][] leerExcel(String rutaArchivo) {
		String[][] lista = null;
		int i = 0;
		try {
			//TODO
			FileInputStream archivo = 
					new FileInputStream(new File(rutaArchivo));
			//Leer el Excel
			HSSFWorkbook archivoExcel = new HSSFWorkbook(archivo);
			//Indicar la Hoja
			HSSFSheet hojaExcel = archivoExcel.getSheetAt(0);
			//Obtener las Filas
			Iterator<Row> filas = hojaExcel.iterator();
			//Salto la primera fila porque es informativa
			filas.next();
			//Crear el arreglo con el tamaño de filas
			lista = new String[hojaExcel.getLastRowNum()][];
			//Recorrer cada una de las filas
			while(filas.hasNext()){
				Row filaActual = filas.next();
				Iterator<Cell> celdas = filaActual.cellIterator();
				lista[i] = new String[filaActual.getLastCellNum()];
				int j=0;
				while(celdas.hasNext()){
					Cell celda = celdas.next();
					lista[i][j] = celda.getStringCellValue();
					j++;
				}
				i++;
			}
			archivoExcel.close();
			archivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	}
}
