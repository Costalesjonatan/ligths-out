package negocio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import assertsAuxiliares.Assert;

public class JuegoTest {

Juego _juego;
	
	@Before
	public void setUp() throws IOException
	{
		_juego = new Juego();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verificarArgumentoNegativo() throws IOException
	{
		_juego.cargar_nivel_especifico(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verificarArgumentoNivelInexistente() throws IOException
	{
		_juego.cargar_nivel_especifico(40);
	}
	
	@Test
	public void verificarEstadoDeLuzEncendidatest()
	{
		_juego.realizar_movimiento(0, 0);
		assertTrue(_juego.verificar_estado_de_luz(0, 0));
	}
	
	@Test
	public void verificarEstadoDeLuzApagadaTest() {
		assertFalse(_juego.verificar_estado_de_luz(0, 0));
	}
	
	@Test
	public void tamañoDelTableroAlConstruirseTest() 
	{
		assertEquals(_juego.obtener_tamaño_de_tablero(), 3);
	}
	
	@Test
	public void nivelActualDelJuegoAlConstruirseTest()
	{
		assertEquals(_juego.obtener_nivel_actual(), 0);
	}
	
	@Test
	public void cargaDeNivelEnElConstructor_test()
	{
		assertTrue(_juego.verificar_estado_de_luz(1, 1));
	}
	
	@Test
	public void realizarMovimientoTest() 
	{
		String esperado = "010\n101\n010\n";
		_juego.realizar_movimiento(1, 1);
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargarSiguienteNivelTest() throws IOException
	{
		String esperado = "1111\n1001\n1001\n1111\n";
		_juego.cargar_siguiente_nivel();
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargarSiguienteNivelTest2() throws IOException
	{
		_juego.cargar_siguiente_nivel();
		assertEquals(_juego.obtener_nivel_actual(), 1);
	}
	
	@Test
	public void cargarNivelEspecificoStringTest() throws IOException
	{
		String esperado = "00000\n01001\n11111\n01001\n00000\n";
		_juego.cargar_nivel_especifico(5);
		assertEquals(_juego.toString(), esperado);
	}
	
	@Test
	public void cargarNivelEspecificoIntTest2() throws IOException
	{
		_juego.cargar_nivel_especifico(5);
		assertEquals(_juego.obtener_nivel_actual(), 5);
	}
	
	@Test
	public void terminoNivelTest()
	{
		_juego.realizar_movimiento(1, 1);
		_juego.realizar_movimiento(1, 0);
		_juego.realizar_movimiento(1, 2);
		_juego.realizar_movimiento(0, 1);
		_juego.realizar_movimiento(2, 1);
		
		assertTrue(_juego.termino_el_nivel());
	}
	
	@Test
	public void noTerminoNivelTest() 
	{
		assertFalse(_juego.termino_el_nivel());
	}
	
	@Test
	public void clonarTableroTest() throws IOException
	{
		boolean[][] esperado = new boolean[3][3];
		esperado[1][1] = true;

		assertTrue(Assert.iguales(_juego.clonar_tablero(), esperado));
	}
	
	@Test
	public void obtenerLaSolucionDelNivelActualTest()
	{
		ArrayList<String> esperado = new ArrayList<String>();
		esperado.add("01");
		esperado.add("10");
		esperado.add("11");
		esperado.add("12");
		esperado.add("21");
	
		assertEquals(_juego.obtener_solucion_del_nivel_actual(), esperado);
	}
	
	@Test
	public void reiniciarJuegoTest() throws IOException
	{
		_juego.cargar_siguiente_nivel();
		_juego.cargar_siguiente_nivel();
		_juego.cargar_siguiente_nivel();
		_juego.cargar_siguiente_nivel();
		_juego.reiniciar_juego();
		assertTrue(_juego.obtener_nivel_actual() == 0);
	}
	
	@Test
	public void ObtenerRecordDeMovimientosTest() throws IOException
	{
		_juego.realizar_movimiento(0, 1);
		_juego.realizar_movimiento(2, 1);
		_juego.realizar_movimiento(0, 2);
		_juego.cargar_siguiente_nivel();
		assertTrue(_juego.obtener_record_de_movimientos() == 3);
		
	}
	
	@Test
	public void ObtenerSugerenciaDeMovimientoTest()
	{
		assertEquals(_juego.obtener_sugerencia_de_movimiento(), "01");
	}
	
	@Test
	public void cantidadDeMovimientosTest()
	{
		_juego.realizar_movimiento(1, 1);
		_juego.realizar_movimiento(1, 0);
		_juego.realizar_movimiento(1, 2);
		assertEquals(_juego.obtener_cantidad_de_movimientos(), 3);
	}
}
