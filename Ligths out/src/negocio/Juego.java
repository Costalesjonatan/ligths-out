package negocio;

import java.io.IOException;

import baseDeDatos.Datos;

public class Juego {
	
	private Tablero _tablero;
	private int nivel_actual;
	
	public Juego() throws IOException
	{
		_tablero = new Tablero(3);
		nivel_actual = 0;
	}
	
	public void cargar_siguiente_nivel() throws IOException
	{
		String siguiente_nivel = Datos.obtenerNivel(nivel_actual++);
		char tamaño = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(tamaño);
		cargar_luces(siguiente_nivel);
	}
	
	public void cargar_nivel_especifico(int nivel) throws IOException
	{
		String siguiente_nivel = Datos.obtenerNivel(nivel);
		char tamaño = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(Integer.parseInt(""+tamaño));
		cargar_luces(siguiente_nivel);
		nivel_actual = nivel;
	}
	
	private void cargar_luces(String nivel_a_cargar) throws IOException
	{
		int i = 0;
		
		for(int j = 0; j < _tablero.getTamaño(); j++)
		{
			for(int k = 0; k < _tablero.getTamaño(); k++)
			{
				if(nivel_a_cargar.charAt(i) == '1')
				{
					_tablero.setLuz(j, k);;
				}
				i++;
			}
		}
	}
	
	public Tablero clonar_tablero()
	{
		return _tablero.clonar();
	}
	
	public boolean termino_el_nivel()
	{
		return _tablero.apagoTodasLasLuces();
	}
	
	public String toString()
	{
		return  _tablero.toString();
	}

}
