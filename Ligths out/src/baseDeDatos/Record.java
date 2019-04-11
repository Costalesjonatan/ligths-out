package baseDeDatos;

import java.io.Serializable;

public class Record implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String _nombre;
	private int _nivel;
	private int _movimientos;
	
	public Record(String nombre, int nivel, int movimientos)
	{
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
	
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		ret.append(_nombre+ " ");
		ret.append(_nivel + " ");
		ret.append(_movimientos);
		
		return ret.toString();
	}
}
