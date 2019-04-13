package baseDeDatos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RecordTest 
{
	
	private Record record;
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarArgumentoNombreTest()
	{
		record  = new Record(null, 15, 60);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarArgumentosNivelNegtivoTest()
	{
		record  = new Record("Kimmy", -1, 60);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarArgumentosNivelSuperior()
	{
		record = new Record("Fanny", 41, 200);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarArgumentosMovimientosTest()
	{
		record  = new Record("Moskov", 15, -1);
	}
	
	@Test
	public void obtenerNombreTest()
	{
		record = new Record("Hanzo", 40, 200);
		assertEquals(record.obtenerNombre(), "Hanzo");
	}
	
	@Test
	public void obtenerNivelTest()
	{
		record = new Record("Esmeralda", 40, 250);
		assertEquals(record.obtenerNivel(), 40);
	}
	
	@Test
	public void obtenerMovimientosTest()
	{
		record = new Record("Kufra", 40, 190);
		assertEquals(record.obtenerMovimeintos(), 190);
	}
	
	@Test
	public void toStringtest()
	{
		record = new Record("Guinevere", 40, 190);
		String esperado = "Guinevere 40 190";
		assertEquals(record.toString(), esperado);
	}
}
