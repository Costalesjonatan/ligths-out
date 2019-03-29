package negocio;

import java.io.IOException;

import baseDeDatos.Datos;

public class Tablero {
	//TODO el dise�o quizas esta requiriendo otra calse que se encargue de tareas que tiene que ver con el juego(reglas de juego) que el tablero en si.
	private boolean[][] _tablero;
	private int nivel;
	//TODO: El constructor que reciba el tama�o del tablero y que despues se encargue la nueva clase de cuando pedir tablero de un tama�o especifico
	public Tablero() throws IOException 
	{
		_tablero = new boolean[3][3];
		nivel = 0;
		cargarNivel();
	}
	
	private Tablero(int tama�o)
	{
		_tablero = new boolean[tama�o][tama�o];
	}
	
	//TODO: deberia estar en la nueva clase
	public void cambiarNivel() throws IOException
	{
		if(apagoTodasLasLuces())
		{
			if(nivel == 0)
			{
				this._tablero = new boolean[4][4];
				nivel++;
				cargarNivel();
			}
			else if(nivel >= 1)
			{
				this._tablero = new boolean[5][5];
				nivel++;
				cargarNivel();
			}
		}
	}
	
	//TODO: refactoriza para que pueda cargar cualquier nivel en cualquier momento que se requiera. deberia estar en la nueva clase
	private void cargarNivel() throws IOException
	{
		String nivelAcargar = Datos.obtenerNivel(nivel);
		
		int i = 0;
		
		for(int j = 0; j < getTama�o(); j++)
		{
			for(int k = 0; k < getTama�o(); k++)
			{
				if(nivelAcargar.charAt(i) == '1')
				{
					_tablero[j][k] = true;
				}
				i++;
			}
		}
	}
	
	private void verificarArgumentos(int i, int j) 
	{
		if(i < 0 || i > (getTama�o()-1)) 
		{
			throw new IllegalArgumentException("la posicion " + i + "no existe!");
		}
		
		if(j < 0 || j > (getTama�o()-1))
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
		
		for(int i = 0; i < getTama�o(); i++) 
			for(int j = 0; j < getTama�o(); j++)
				ret = ret && !estaEncendida(i,j); //si apago todas las luces ret no cambia
		
		return ret;
	}
	
	public boolean estaEncendida(int i, int j) 
	{
		verificarArgumentos(i,j);
		return _tablero[i][j];
	}
	
	public int getTama�o() {
		return _tablero.length;
	}
	
	private void setLuz(int i, int j)
	{
		_tablero[i][j] = true;
	}
	
	public Tablero clonar()
	{
		Tablero ret = new Tablero(getTama�o());
		
		for(int i = 0; i < getTama�o(); i++)
		{
			for(int j = 0; j < getTama�o(); j++)
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
		
		for(int i = 0; i < getTama�o(); i++)
		{
			for(int j = 0; j < getTama�o(); j++) 
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
