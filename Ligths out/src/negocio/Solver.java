package negocio;

import java.util.ArrayList;

public class Solver {
	//TODO: esta clase deberia ser un objeto! definitivamente tiene que ser un objeto;
	private static ArrayList<ArrayList<Integer>> soluciones;
	private static ArrayList<ArrayList<String>> caminos_de_movimientos;
	private static ArrayList<Integer> cantidad_de_movimientos;
	private static ArrayList<String> camino_de_movimiento;
	private static ArrayList<ArrayList<Integer>> combinaciones;
	private static int contador_de_movimientos;
	private static String movimiento;
	private static Tablero clon;
	
	public static String sugerir_movmiento(Juego juego)
	{
		posibles_soluciones(juego);
		int movimiento = mejor_proximo_movimiento();
		
		return caminos_de_movimientos.get(cantidad_de_movimientos.indexOf(movimiento)).get(0);
	}
	
	public static ArrayList<String> solucion(Juego juego)
	{
		posibles_soluciones(juego);
		int movimiento = mejor_proximo_movimiento();
		
		return caminos_de_movimientos.get(cantidad_de_movimientos.indexOf(movimiento));
	}

	private static void posibles_soluciones(Juego juego)
	{
		clon = juego.clonar_tablero();
		soluciones = new ArrayList<ArrayList<Integer>>();
		combinaciones = Solver.binarios(clon.getTamaño());
		caminos_de_movimientos  = new ArrayList<ArrayList<String>>();
		cantidad_de_movimientos = new ArrayList<Integer>();
		camino_de_movimiento = new ArrayList<String>();
		
		for(ArrayList<Integer> combinacion: combinaciones)
		{
			clon = juego.clonar_tablero();
			if(Solver.solver(combinacion))
			{	
				soluciones.add(combinacion);
				cantidad_de_movimientos.add(contador_de_movimientos);
				caminos_de_movimientos.add(camino_de_movimiento);
			}
		}
	}
	
	private static int mejor_proximo_movimiento()
	{
		int mejor_solucion = 0;
		
		for(Integer movimientos: cantidad_de_movimientos)
		{
			if(mejor_solucion == 0) 
			{
				mejor_solucion = movimientos;
			}
			else if(movimientos < mejor_solucion)
			{
				mejor_solucion = movimientos;
			}
		}
		return mejor_solucion;
	}

	private static boolean solver(ArrayList<Integer> combinacion)
	{
		contador_de_movimientos = 0;
		camino_de_movimiento = new ArrayList<String>();
		
		for(int i = 0; i < combinacion.size(); i++)
		{
			if(combinacion.get(i) == 1)
			{
				clon.realizarMoviento(0, i);
				contador_de_movimientos++;
				movimiento = ""+0+i;
				camino_de_movimiento.add(movimiento);
			}
		}
		for(int i = 0; i < clon.getTamaño()-1; i++)
		{
			for(int j = 0; j < clon.getTamaño(); j++)
			{
				if(clon.estaEncendida(i, j))
				{
					clon.realizarMoviento(i+1, j);
					contador_de_movimientos++;
					movimiento = ""+(i+1)+j;
					camino_de_movimiento.add(movimiento);
				}
			}
		}
		return clon.apagoTodasLasLuces();
	}
	
	
	private static ArrayList<ArrayList<Integer>> binarios(int tamañoDeTablero)
	{
		combinaciones = new ArrayList<ArrayList<Integer>>();
		
		contar_en_binario(tamañoDeTablero);
		completar_binarios(tamañoDeTablero);
		
		return combinaciones;
	}
	
	private static void contar_en_binario(int tamañoDeTablero)
	{
		int resto;
		int limite = (int) Math.pow(2, tamañoDeTablero);
		
		for(int i = 0; i < limite; i++)
		{
			ArrayList<Integer> combinacion = new ArrayList<Integer>();
			int numero = i;
			do 
			{
				resto = numero%2;
				numero = numero/2;
				combinacion.add(0, resto);
			}while(numero >= 2);
			
			combinacion.add(0, numero);
			combinaciones.add(combinacion);
		}
	}
	
	private static void completar_binarios(int tamañoDeTablero)
	{
		for(ArrayList<Integer> numeroBinario: combinaciones)
		{
			if(numeroBinario.size() < tamañoDeTablero)
			{
				for(int i = numeroBinario.size(); i < tamañoDeTablero; i++)
				{
					numeroBinario.add(0, 0);
				}
			}
		}
	}
}
