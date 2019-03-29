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
		tablero = new Tablero();
		esperado = new boolean[3][3];
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarPosicionValidaTest()
	{
		tablero.realizarMoviento(3, 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarPosicionValidaTest2()
	{
		tablero.realizarMoviento(-1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarPosicionValidaTest3()
	{
		tablero.realizarMoviento(1, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarPosicionValidaTest4()
	{
		tablero.realizarMoviento(2, -1);
	}
	
	@Test
	public void movimientoTest() 
	{
		esperado[0][1] = true;
		esperado[1][0] = true;
		esperado[1][2] = true;
		esperado[2][1] = true;
		
		tablero.realizarMoviento(1, 1);
		
		assertTrue(Assert.iguales(tablero, esperado));
	}
	
	@Test
	public void casosBordeTest() {
		
		tablero.realizarMoviento(2, 2);
		tablero.realizarMoviento(2, 1);
		esperado[2][0] = true;
		esperado[1][2] = true;
		
		assertTrue(Assert.iguales(tablero, esperado));
	}
	
	@Test
	public void apagoTodasLasLucesTest() 
	{
		
		tablero.realizarMoviento(1, 1);
		tablero.realizarMoviento(1, 2);
		tablero.realizarMoviento(1, 0);
		tablero.realizarMoviento(2, 1);
		tablero.realizarMoviento(0, 1);
		
		assertTrue(tablero.apagoTodasLasLuces());
	}
	
	@Test
	public void noApagoTodasLasLuces() 
	{
		assertFalse(tablero.apagoTodasLasLuces());
	}
	
	@Test
	public void verificarTamaño() 
	{
		assertEquals(tablero.getTamaño(),3);
	}
	
	@Test
	public void verificarToString() 
	{
		StringBuilder esperado = new StringBuilder();
		esperado.append("000\n010\n000\n");
		assertTrue(tablero.toString().equals(esperado.toString()));
	}
	
	@Test
	public void cambiarNivelTest() throws IOException 
	{
		
		tablero.realizarMoviento(1, 1);
		tablero.realizarMoviento(1, 2);
		tablero.realizarMoviento(1, 0);
		tablero.realizarMoviento(2, 1);
		tablero.realizarMoviento(0, 1);
		
		tablero.cambiarNivel();
		
		assertEquals(tablero.getTamaño(), 4);
	}
	
	@Test
	public void cambiarNivelTest2()
	{
		
	}
}
