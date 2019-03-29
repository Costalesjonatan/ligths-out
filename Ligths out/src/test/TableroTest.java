package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import negocio.Tablero;

public class TableroTest {
	
	Tablero tablero;
	boolean[][] esperado;
	
	
	@Before
	public void setUp() throws IOException {
		tablero = new Tablero(3);
		esperado = new boolean[3][3];
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_tamaño_0_test()
	{
		tablero = new Tablero(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_tamaño_negativo_test()
	{
		tablero = new Tablero(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_posicion_negativa_test()
	{
		tablero.realizarMoviento(-1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_posicion_negativa_test2()
	{
		tablero.realizarMoviento(0, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_posicion_excedida_test()
	{
		tablero.realizarMoviento(1, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificar_posicion_excedida_test2()
	{
		tablero.realizarMoviento(3, 1);
	}

	@Test
	public void movimientoTest() 
	{
		esperado[0][1] = true;
		esperado[1][0] = true;
		esperado[1][2] = true;
		esperado[2][1] = true;
		esperado[1][1] = true;
		
		tablero.realizarMoviento(1, 1);
		
		assertTrue(Assert.iguales(tablero, esperado));
	}
	
	@Test
	public void verificar_movimientos_test() {
		
		tablero.realizarMoviento(1, 1);
		tablero.realizarMoviento(0, 0);
		tablero.realizarMoviento(0, 2);
		tablero.realizarMoviento(1, 1);
		tablero.realizarMoviento(0, 0);
		tablero.realizarMoviento(0, 2);
		
		assertTrue(Assert.iguales(tablero, esperado));
	}
	
	@Test
	public void apago_todas_las_luces_test() 
	{	
		assertTrue(tablero.apagoTodasLasLuces());
	}
	
	@Test
	public void no_apago_todas_las_luces() 
	{
		tablero.realizarMoviento(1, 1);
		assertFalse(tablero.apagoTodasLasLuces());
	}
	
	@Test
	public void verificar_tamaño() 
	{
		assertEquals(tablero.getTamaño(),3);
	}
	
	@Test
	public void verificar_set_luz()
	{
		tablero.setLuz(2, 2);
		esperado[2][2] = true;
		
		assertTrue(Assert.iguales(tablero, esperado));
	}
	
	@Test
	public void clonar_tablero_test()
	{
		tablero.realizarMoviento(1, 1);
		Tablero clon = tablero.clonar_tablero();
		esperado[1][1] = true;
		esperado[1][0] = true;
		esperado[1][2] = true;
		esperado[0][1] = true;
		esperado[2][1] = true;
		assertTrue(Assert.iguales(clon, esperado));
	}
	
	@Test
	public void verificar_to_string() 
	{
		tablero.realizarMoviento(0, 0);
		StringBuilder esperado = new StringBuilder();
		esperado.append("110\n100\n000\n");
		assertTrue(tablero.toString().equals(esperado.toString()));
	}
}
