package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import negocio.Juego;

public class JuegoTest {
	
	Juego _juego;
	
	@Before
	public void setUp() throws IOException
	{
		_juego = new Juego();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verificar_argumento_negativo() throws IOException
	{
		_juego.cargar_nivel_especifico(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verificar_argumento_nivel_inexistente() throws IOException
	{
		_juego.cargar_nivel_especifico(12);
	}
	
	@Test
	public void verificar_estado_de_luz_encendida_test()
	{
		_juego.realizar_movimiento(0, 0);
		assertTrue(_juego.verificar_estado_de_luz(0, 0));
	}
	
	@Test
	public void verificar_estado_de_luz_apagada_test() {
		assertFalse(_juego.verificar_estado_de_luz(0, 0));
	}
	
	@Test
	public void tamaño_del_tablero_al_construirse_test() 
	{
		assertEquals(_juego.obtener_tamaño_de_tablero(), 3);
	}
	
	@Test
	public void nivel_actual_del_juego_al_construirse_test2()
	{
		assertEquals(_juego.obtener_nivel_actual(), 0);
	}
	
	@Test
	public void carga_de_nivel_en_el_constructor_test()
	{
		assertTrue(_juego.verificar_estado_de_luz(1, 1));
	}
	
	@Test
	public void realizar_movimiento_test() 
	{
		String esperado = "010\n101\n010\n";
		_juego.realizar_movimiento(1, 1);
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargar_siguiente_nivel_test() throws IOException
	{
		String esperado = "1111\n1001\n1001\n1111\n";
		_juego.cargar_siguiente_nivel();
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargar_siguiente_nivel_test2() throws IOException
	{
		_juego.cargar_siguiente_nivel();
		assertEquals(_juego.obtener_nivel_actual(), 1);
	}
	
	@Test
	public void cargar_nivel_especifico_test() throws IOException
	{
		String esperado = "00000\n01001\n11111\n01001\n00000\n";
		_juego.cargar_nivel_especifico(5);
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargar_nivel_especifico_test2() throws IOException
	{
		_juego.cargar_nivel_especifico(5);
		assertEquals(_juego.obtener_nivel_actual(), 5);
	}
	
	@Test
	public void termino_nivel_test()
	{
		_juego.realizar_movimiento(1, 1);
		_juego.realizar_movimiento(1, 0);
		_juego.realizar_movimiento(1, 2);
		_juego.realizar_movimiento(0, 1);
		_juego.realizar_movimiento(2, 1);
		
		assertTrue(_juego.termino_el_nivel());
	}
	
	@Test
	public void no__termino_nivel_test() 
	{
		assertFalse(_juego.termino_el_nivel());
	}
	
	@Test
	public void clonar_tablero_test() throws IOException
	{
		boolean[][] esperado = new boolean[3][3];
		esperado[1][1] = true;

		assertTrue(Assert.iguales(_juego.clonar_tablero(), esperado));
	}
}
