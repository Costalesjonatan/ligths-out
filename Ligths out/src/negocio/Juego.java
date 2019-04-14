package negocio;

import java.io.IOException;
import java.util.ArrayList;

import baseDeDatos.Datos;
import baseDeDatos.Records;
//TODO: hace los casos de test de los metodos que gargaste
public class Juego {
	
	private Tablero _tablero;
	private int nivel_actual;
	private int contador_de_movimientos;
	private int record_de_movimientos;
	
	public Juego() throws IOException
	{
		_tablero = new Tablero(3);
		nivel_actual = 0;
		contador_de_movimientos = 0;
		record_de_movimientos = 0;
		cargar_nivel_especifico(0);
	}
	
	public void realizar_movimiento(int i, int j)
	{
		_tablero.realizarMoviento(i, j);
		contador_de_movimientos++;
	}
	
	public void cargar_siguiente_nivel() throws IOException
	{
		String siguiente_nivel = Datos.obtenerNivel((nivel_actual+1));
		char tamaño = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(Integer.parseInt(""+tamaño));
		_tablero.cargar_luces(siguiente_nivel);
		nivel_actual+=1;
		record_de_movimientos+=contador_de_movimientos;
		contador_de_movimientos = 0;
	}
	
	public void cargar_nivel_especifico(int nivel) throws IOException
	{
		verificar_argumento(nivel);
		String siguiente_nivel = Datos.obtenerNivel(nivel);
		char tamaño = siguiente_nivel.charAt(siguiente_nivel.length()-1);
		_tablero = new Tablero(Integer.parseInt(""+tamaño));
		_tablero.cargar_luces(siguiente_nivel);
		nivel_actual = nivel;
		contador_de_movimientos = 0;
	}
	
	private void verificar_argumento(int nivel)
	{
		if(nivel < 0)
		{
			throw new IllegalArgumentException("no existen niveles menores a 0, usted requirio el nivel: " + nivel);
		}
		if(nivel > 99)
		{
			throw new IllegalArgumentException("El ultimo nivel disponible es el 39, usted requirio el nivel: " + nivel);

		}
	}
	
	public ArrayList<String> obtener_solucion_del_nivel_actual()
	{
		return Solver.solucion(this);
	}
	
	public String obtener_sugerencia_de_movimiento()
	{
		return Solver.sugerir_movmiento(this);
	}
	
	public void reiniciar_juego() throws IOException
	{
		cargar_nivel_especifico(0);
		nivel_actual = 0;
		record_de_movimientos = 0;
		contador_de_movimientos = 0;
	}
	

	public void guardarRecord(String nombre) throws IOException 
	{
		record_de_movimientos+=contador_de_movimientos;
		Records records = Datos.obtenerRecords();
		records.analizarRecordNuevo(nombre, this.record_de_movimientos);
		Datos.guardarRecords(records);
	}
	
	public Records cargarRecords() throws IOException
	{
		return Datos.obtenerRecords();
	}
	
	public Tablero clonar_tablero()
	{
		return _tablero.clonar_tablero();
	}
	
	public boolean termino_el_nivel()
	{
		return _tablero.apagoTodasLasLuces();
	}
	
	public int obtener_tamaño_de_tablero()
	{
		return _tablero.getTamaño();
	}
	
	public boolean verificar_estado_de_luz(int i, int j)
	{
		return _tablero.estaEncendida(i, j);
	}
	
	public int obtener_nivel_actual()
	{
		return nivel_actual;
	}
	
	public int obtener_cantidad_de_movimientos()
	{
		return  contador_de_movimientos;
	}
	
	public int obtener_record_de_movimientos()
	{
		return record_de_movimientos;
	}
	
	public String toString()
	{
		return  _tablero.toString();
	}
}
