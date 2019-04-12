package baseDeDatos;

import java.io.Serializable;

public class Records implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Record[] _records;
	
	public Records()
	{
		_records = new Record[10];
		
		_records[0] = new Record("Esmeralda", 40, 200);
		_records[1] = new Record("Fanny", 40, 250);
		_records[2] = new Record("Aldous", 40, 199);
		_records[3] = new Record("Karina", 40, 215);
		_records[4] = new Record("Nana", 40, 201);
		_records[5] = new Record("Eudora", 40, 205);
		_records[6] = new Record("Lesly", 40, 195);
		_records[7] = new Record("Moskov", 40, 235);
		_records[8] = new Record("Kufra", 40, 306);
		_records[9] = new Record("Grock", 40, 352);

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
	
	public void ordenarRecords()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(this._records[i].obtenerMovimeintos() < this._records[j].obtenerMovimeintos())
				{
					intercambiar(i, j);
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
		
		if(movimientos < this._records[9].obtenerMovimeintos())
		{
			int i = 0;
			
			while(movimientos > this._records[i].obtenerMovimeintos())
			{
				i++;
			}
			Record record = new Record(nombre, 40, movimientos);
			this._records[9] = record;
			ordenarRecords();
		}
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
