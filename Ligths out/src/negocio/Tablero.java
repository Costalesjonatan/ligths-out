package negocio;

import java.io.IOException;

public class Tablero {
	//TODO el diseño quizas esta requiriendo otra calse que se encargue de tareas que tiene que ver con el juego(reglas de juego) que el tablero en si.
	private boolean[][] _tablero;
	
	//TODO: El constructor que reciba el tamaño del tablero y que despues se encargue la nueva clase de cuando pedir tablero de un tamaño especifico
	public Tablero() throws IOException 
	{
		_tablero = new boolean[3][3];
	}
	
	public Tablero(int tamaño)
	{
		_tablero = new boolean[tamaño][tamaño];
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
			for(int j = 0; j < getTamaño(); j++)
				ret = ret && !estaEncendida(i,j); //si apago todas las luces ret no cambia
		
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
		_tablero[i][j] = true;
	}
	
	public Tablero clonar()
	{
		Tablero ret = new Tablero(getTamaño());
		
		for(int i = 0; i < getTamaño(); i++)
		{
			for(int j = 0; j < getTamaño(); j++)
			{
				if(estaEncendida(i,j))
				{
					ret.setLuz(i, j);
				}
			}
		}
		
		return ret;
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
