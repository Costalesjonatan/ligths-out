package baseDeDatos;

import java.io.Serializable;

public class Records implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Record[] _records;
	
	public Records()
	{
		_records = new Record[10];
	}
	
	public String obtenerNombreDeRecord(int posicion)
	{
		return _records[posicion].obtenerNombre();
	}
	
	public int obtenerNivelDeRecord(int posicion)
	{
		return _records[posicion].obtenerNivel();
	}
	
	public int obtenerMovimientosDeRecord(int posicion)
	{
		return _records[posicion].obtenerMovimeintos();
	}
	
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		for(int i = 0; i < 10; i++)
		{
			ret.append(_records[i].toString());
			ret.append("\n");
		}
		return ret.toString();
	}
}
