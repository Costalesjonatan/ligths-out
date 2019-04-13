package baseDeDatos;

import java.io.Serializable;

public class Record implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String _nombre;
	private int _nivel;
	private int _movimientos;
	
	public Record(String nombre, int nivel, int movimientos)
	{
		verificarArgumentos(nombre, nivel, movimientos);
		_nombre = nombre;
		_nivel = nivel;
		_movimientos = movimientos;
	}
	
	public String obtenerNombre()
	{
		return _nombre;
	}
	
	public int obtenerNivel()
	{
		return _nivel;
	}
	
	public int obtenerMovimeintos()
	{
		return _movimientos;
	}
	
	private void verificarArgumentos(String nombre, int nivel, int movimientos)
	{
		if(nombre == null)
		{
			throw new IllegalArgumentException("Debe ingresar un nombre!");
		}
		if(nivel < 0 || nivel > 40)
		{
			throw new IllegalArgumentException("El nivel especificdo no existe: " + nivel );
		}
		if(movimientos < 0)
		{
			throw new IllegalArgumentException("La cantidad de movimeintos debe ser mayor a 0");
		}
	}
	
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		ret.append(_nombre+ " ");
		ret.append(_nivel + " ");
		ret.append(_movimientos);
		
		return ret.toString();
	}
}
