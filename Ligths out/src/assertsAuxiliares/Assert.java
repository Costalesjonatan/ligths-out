package assertsAuxiliares;

import negocio.Tablero;

public class Assert {
	
	
	public static boolean iguales(Tablero tablero, boolean[][] esperado) 
	{
		for(int i = 0; i < tablero.getTama�o(); i++) {
			for(int j = 0; j < tablero.getTama�o(); j++) {
				if(tablero.estaEncendida(i, j) != esperado[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
}
