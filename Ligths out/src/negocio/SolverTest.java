package negocio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SolverTest {
	
	private Juego juego;
	
	@Before
	public void setUp() throws IOException
	{
		juego = new Juego();
	}
	
	
	@Test
	public void sugerir_movimiento_test() throws IOException
	{
		String sugerencia = Solver.sugerir_movmiento(juego);
		String esperado = "01";
		assertEquals(sugerencia, esperado);
	}
	
	@Test
	public void solucion_test()
	{
		ArrayList<String> solucion = Solver.solucion(juego);
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("01");
		esperado.add("10");
		esperado.add("11");
		esperado.add("12");
		esperado.add("21");
		
		assertEquals(esperado, solucion);
	}
}
