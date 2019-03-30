package negocio;

import java.util.ArrayList;

public class Solver {
	
	private static int contador_de_movimientos;
	private static ArrayList<ArrayList<Integer>> posibles_soluciones = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Integer> cantidad_de_movimientos = new ArrayList<Integer>();
	private static Tablero clon;
	private static ArrayList<ArrayList<Integer>> combinaciones;
	
	public static ArrayList<Integer> proximo_movimiento(Juego juego)
	{
		clon = juego.clonar_tablero();
		combinaciones = Solver.binarios(clon.getTamaño());
		
		for(ArrayList<Integer> combinacion: combinaciones)
		{
			clon = juego.clonar_tablero();
			if(Solver.solver(combinacion))
			{	
				posibles_soluciones.add(combinacion);
				cantidad_de_movimientos.add(contador_de_movimientos);
				//TODO: cuando este termiando borralo
				for(Integer digito: combinacion)
				{
					
					System.out.print(digito + ",");
				}
				System.out.println();
			}
		}
		return posibles_soluciones.get(cantidad_de_movimientos.indexOf(mejor_proximo_movimiento()));
	}
	
	public static int mejor_proximo_movimiento()
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
	
	//TODO:tenes que refactorizar para que devuleva la opcion con la cual se resuelva en menos movimientos
	public static boolean solver(ArrayList<Integer> combinacion)
	{
		contador_de_movimientos = 0;
		
		for(int i = 0; i < combinacion.size(); i++)
		{
			if(combinacion.get(i) == 1)
			{
				clon.realizarMoviento(0, i);
				contador_de_movimientos++;
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
				}
			}
		}
		System.out.println(contador_de_movimientos);
		return clon.apagoTodasLasLuces();
	}
	
	
	public static ArrayList<ArrayList<Integer>> binarios(int tamañoDeTablero)
	{
		combinaciones = new ArrayList<ArrayList<Integer>>();
		
		contar_en_binario(tamañoDeTablero);
		completar_binarios(tamañoDeTablero);
		
		return combinaciones;
	}
	
	public static void contar_en_binario(int tamañoDeTablero)
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
	
	public static void completar_binarios(int tamañoDeTablero)
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
