package negocio;

import java.io.IOException;
import java.util.ArrayList;

public class Solver {
	
	public static void main(String[] args)
	{
		ArrayList<ArrayList<Integer>> combinaciones = binarios(5);
		
		Tablero tablero = null;
		
		try {
			tablero = new Tablero();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tablero.realizarMoviento(1, 1);
		tablero.realizarMoviento(1, 2);
		tablero.realizarMoviento(1, 0);
		tablero.realizarMoviento(2, 1);
		tablero.realizarMoviento(0, 1);
		try {
			tablero.cambiarNivel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tablero.realizarMoviento(0, 0);
		tablero.realizarMoviento(0, 3);
		tablero.realizarMoviento(3, 0);
		tablero.realizarMoviento(3, 3);
		
		try {
			tablero.cambiarNivel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for(ArrayList<Integer> combinacion: combinaciones)
		{
			Tablero posibleSolucion = tablero.clonar();
			if(solver(posibleSolucion, combinacion))
			{	
				for(Integer digito: combinacion)
				{
					System.out.print(digito + ",");
				}
				System.out.println();
			}
		}
	}
	//TODO:tenes que refactorizar para que devuleva la opcion con la cual se resuelva en menos movimientos
	public static boolean solver(Tablero tablero, ArrayList<Integer> combinacion)
	{
		int cantidad_de_movimientos = 0;
		for(int i = 0; i < combinacion.size(); i++)
		{
			if(combinacion.get(i) == 1)
			{
				tablero.realizarMoviento(0, i);
				cantidad_de_movimientos++;
			}
		}
		
		for(int i = 0; i < tablero.getTamaño()-1; i++)
		{
			for(int j = 0; j < tablero.getTamaño(); j++)
			{
				if(tablero.estaEncendida(i, j))
				{
					tablero.realizarMoviento(i+1, j);
					cantidad_de_movimientos++;
				}
			}
		}
		System.out.println(cantidad_de_movimientos);
		return tablero.apagoTodasLasLuces();
	}
	
	public static ArrayList<ArrayList<Integer>> binarios(int tamañoDeTablero)
	{
		ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<ArrayList<Integer>>();
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
		return combinaciones;
	}
}
