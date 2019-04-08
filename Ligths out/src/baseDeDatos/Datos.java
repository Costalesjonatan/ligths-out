package baseDeDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Datos {

	
	public static String obtenerNivel(int nivel) throws IOException
	{
		verificarArgumento(nivel);
		BufferedReader bf = new BufferedReader(new FileReader("recursos/niveles.txt"));
		
		int i = 0;
		String nivelAcargar = null;
		
		if(nivel == 0)
		{
			nivelAcargar = bf.readLine();
			bf.close();
			return nivelAcargar;
		}
		else
		{
			while (i <= nivel)
			{
				nivelAcargar = bf.readLine();
				i++;
			}
		}
		
		bf.close();
		return nivelAcargar;
	}
	
	public static void guardarDatosDeRecord(int cantidad_de_moviemintos_total) throws IOException 
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("recursos/record.txt"));
		BufferedReader bf = new BufferedReader(new FileReader("recursos/record.txt"));
		
		String record_actual = bf.readLine();
		if(record_actual == null)
		{
			bw.write(cantidad_de_moviemintos_total + "");
		}
		else if(cantidad_de_moviemintos_total < Integer.parseInt(record_actual))
		{
			bw.write(cantidad_de_moviemintos_total + "");
		}
		
		bw.close();
		bf.close();
	}
	
	public static String obtenerUltimoRecord() throws IOException
	{
		BufferedReader bf = new BufferedReader(new FileReader("recursos/record.txt"));
		String ultimo_record = bf.readLine();
		bf.close();
		return ultimo_record;
		
	}
	
	private static void verificarArgumento(int nivel)
	{
		if(nivel < 0)
		{
			throw new IllegalArgumentException("No existen niveles inferiores a 0");
		}
	}
}
