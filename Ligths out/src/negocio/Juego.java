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
		cargar_nivel_especifico(0);
	}
	
	public void realizar_movimiento(int i, int j)
	{
		_tablero.realizarMoviento(i, j);
	}
	
	public void cargar_siguiente_nivel() throws IOException
	{
		String siguiente_nivel = Datos.obtenerNivel((nivel_actual+1));
		char tama�o = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(Integer.parseInt(""+tama�o));
		cargar_luces(siguiente_nivel);
		nivel_actual+=1;
	}
	
	public void cargar_nivel_especifico(int nivel) throws IOException
	{
		verificar_argumento(nivel);
		String siguiente_nivel = Datos.obtenerNivel(nivel);
		char tama�o = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(Integer.parseInt(""+tama�o));
		cargar_luces(siguiente_nivel);
		nivel_actual = nivel;
	}
	
	private void verificar_argumento(int nivel)
	{
		if(nivel < 0)
		{
			throw new IllegalArgumentException("no existen niveles menores a 0, usted requirio el nivel: " + nivel);
		}
		if(nivel > 39)
		{
			throw new IllegalArgumentException("El ultimo nivel disponible es el 39, usted requirio el nivel: " + nivel);

		}
	}
	
	private void cargar_luces(String nivel_a_cargar) throws IOException
	{
		int i = 0;
		
		for(int j = 0; j < _tablero.getTama�o(); j++)
		{
			for(int k = 0; k < _tablero.getTama�o(); k++)
			{
				if(nivel_a_cargar.charAt(i) == '1')
				{
					_tablero.setLuz(j, k);
				}
				i++;
			}
		}
	}
	
	public Tablero clonar_tablero()
	{
		return _tablero.clonar_tablero();
	}
	
	public boolean termino_el_nivel()
	{
		return _tablero.apagoTodasLasLuces();
	}
	
	public int obtener_tama�o_de_tablero()
	{
		return _tablero.getTama�o();
	}
	
	public boolean verificar_estado_de_luz(int i, int j)
	{
		return _tablero.estaEncendida(i, j);
	}
	
	public int obtener_nivel_actual()
	{
		return nivel_actual;
	}
	
	public String toString()
	{
		return  _tablero.toString();
	}

}
