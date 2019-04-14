package baseDeDatos;

import java.io.Serializable;

public class Records implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Record[] _records;
	
	public Records()
	{
		_records = new Record[10];
	}
	
	public void agregarRecord(String nombre, int nivel, int movimientos)
	{
		if(_records[9] != null)
		{
			throw new RuntimeException("Se Alcanzo el maximo de records");
		}
		int i = 0;
		while(i < 10)
		{
			if(_records[i] == null)
			{
				_records[i] = new Record(nombre, nivel, movimientos);
				i = 10;
			}
			else
			{
				i++;
			}
		}
	}
	
	public String obtenerNombreDeRecord(int posicion)
	{
		verificarArgumentos(posicion);
		return _records[posicion].obtenerNombre();
	}
	
	public int obtenerNivelDeRecord(int posicion)
	{
		verificarArgumentos(posicion);
		return _records[posicion].obtenerNivel();
	}
	
	public int obtenerMovimientosDeRecord(int posicion)
	{
		verificarArgumentos(posicion);
		return _records[posicion].obtenerMovimeintos();
	}
	
	private void verificarArgumentos(int posicion)
	{
		if(posicion < 0 || posicion > 9)
		{
			throw new IndexOutOfBoundsException("La posicion " + posicion + "no existe");
		}
	}
	
	public void ordenarRecords()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(this._records[i] != null && this._records[j] != null)
				{
					if(this._records[i].obtenerMovimeintos() < this._records[j].obtenerMovimeintos())
					{
						intercambiar(i, j);
					}
				}
			}
		}
	}
	
	private void intercambiar(int posicionI, int posicionJ)
	{
		Record record = this._records[posicionI];
		this._records[posicionI] = this._records[posicionJ];
		this._records[posicionJ] = record;
	}
	
	public void analizarRecordNuevo(String nombre, int movimientos)
	{
		if(_records[9] == null)
		{
			agregarRecord(nombre, 40, movimientos);
			ordenarRecords();
		}
		else
		{
			ordenarRecords();
			if(movimientos < this._records[9].obtenerMovimeintos())
			{
				int i = 0;
				
				while(movimientos > this._records[i].obtenerMovimeintos())
				{
					i++;
				}
				Record record = new Record(nombre, 80, movimientos);
				this._records[9] = record;
				ordenarRecords();
			}
		}
	}

	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		for(int i = 0; i < 10; i++)
		{
			if(_records[i] != null)
			{
				ret.append(_records[i].toString());
				ret.append("\n");
			}
		}
		return ret.toString();
	}
}
