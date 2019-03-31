package negocio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SolverTest {

	private ArrayList<ArrayList<Integer>> todas_las_soluciones;
	private Juego juego;
	
	@Before
	public void setUp() throws IOException
	{
		juego = new Juego();
		todas_las_soluciones = new ArrayList<ArrayList<Integer>>();
	}
	
	@Test
	public void todas_las_soluciones_test() throws IOException
	{
		for(int i = 0; i <= 38; i++)
		{
			todas_las_soluciones.add(Solver.proximo_movimiento(juego));
			juego.cargar_siguiente_nivel();
		}
		todas_las_soluciones.add(Solver.proximo_movimiento(juego));
		
		assertEquals(todas_las_soluciones.size(), 40);
	}
}
