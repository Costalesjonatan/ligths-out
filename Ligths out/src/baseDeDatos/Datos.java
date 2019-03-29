package baseDeDatos;

import java.io.BufferedReader;
import java.io.FileReader;
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
	
	private static void verificarArgumento(int nivel)
	{
		if(nivel < 0)
		{
			throw new IllegalArgumentException("No existen niveles inferiores a 0");
		}
	}
	
}
