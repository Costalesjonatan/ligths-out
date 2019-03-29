package negocio;

public class Tablero {

	private boolean[][] _tablero;

	public Tablero(int tamaño)
	{
		verificar_tamaño();
		_tablero = new boolean[tamaño][tamaño];
	}
	
	private void verificar_tamaño() 
	{
		
	}

	private void verificarArgumentos(int i, int j) 
	{
		if(i < 0 || i > (getTamaño()-1)) 
		{
			throw new IllegalArgumentException("la posicion " + i + "no existe!");
		}
		
		if(j < 0 || j > (getTamaño()-1))
		{
			throw new IllegalArgumentException("la posicion " + j + "no existe!");
		}
	}
	
	public void realizarMoviento(int i, int j) 
	{
		verificarArgumentos(i,j);
		_tablero[i][j] = !_tablero[i][j];//la luz que presiono
		if(i+1 < _tablero.length)
			_tablero[i+1][j] = !_tablero[i+1][j]; //abajo
		if(j-1 >= 0) 
			_tablero[i][j-1] = !_tablero[i][j-1]; //izquierda
		if(j+1 < _tablero.length)
			_tablero[i][j+1] = !_tablero[i][j+1]; //derecha
		if(i-1 >= 0)
			_tablero[i-1][j] = !_tablero[i-1][j]; //arriba
	}
	
	public boolean apagoTodasLasLuces() 
	{
		boolean ret = true;
		
		for(int i = 0; i < getTamaño(); i++)
		{
			for(int j = 0; j < getTamaño(); j++)
			{
				ret = ret && !estaEncendida(i,j); //si apago todas las luces ret no cambia
			}
		}
		
		return ret;
	}
	
	public boolean estaEncendida(int i, int j) 
	{
		verificarArgumentos(i,j);
		return _tablero[i][j];
	}
	
	public int getTamaño() {
		return _tablero.length;
	}
	
	public void setLuz(int i, int j)
	{
		verificarArgumentos(i,j);
		_tablero[i][j] = true;
	}
	
	public String toString() 
	{
		StringBuilder cadenaTablero = new StringBuilder();
		
		for(int i = 0; i < getTamaño(); i++)
		{
			for(int j = 0; j < getTamaño(); j++) 
			{
				if(_tablero[i][j])
				{
					cadenaTablero.append(1);
				}else
				{
					cadenaTablero.append(0);
				}		
			}
			cadenaTablero.append('\n');
		}
		return cadenaTablero.toString();
	}
}
