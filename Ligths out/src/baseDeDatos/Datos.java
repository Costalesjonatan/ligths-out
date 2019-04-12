package baseDeDatos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	
	public static Records obtenerRecords()
	{
		Records records = null;
		
		try
		{
			FileInputStream fis = new FileInputStream("recursos/record.txt");
			ObjectInputStream in = new ObjectInputStream(fis);
			records = (Records) in.readObject();
			in.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return records;
	}
	
	
	public static void guardarRecords(Records records) throws IOException 
	{
		try 
		{
			FileOutputStream fos = new FileOutputStream("recursos/record.txt");
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(records);
			out.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private static void verificarArgumento(int nivel)
	{
		if(nivel < 0)
		{
			throw new IllegalArgumentException("No existen niveles inferiores a 0");
		}
	}
}
