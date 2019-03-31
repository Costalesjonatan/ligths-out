package assertsAuxiliares;

import negocio.Tablero;

public class Assert {
	
	
	public static boolean iguales(Tablero tablero, boolean[][] esperado) 
	{
		for(int i = 0; i < tablero.getTamaño(); i++) {
			for(int j = 0; j < tablero.getTamaño(); j++) {
				if(tablero.estaEncendida(i, j) != esperado[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
}
