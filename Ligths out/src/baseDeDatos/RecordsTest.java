package baseDeDatos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RecordsTest 
{
	private Records records;
	private Records esperado;
	
	@Before
	public void setUp()
	{
		records = new Records();
		records.agregarRecord("Esmeralda", 80, 200);
		records.agregarRecord("Fanny", 80, 250);
		records.agregarRecord("Aldous", 80, 199);
		records.agregarRecord("Karina", 80, 215);
		records.agregarRecord("Nana", 80, 201);
		records.agregarRecord("Eudora", 80, 205);
		records.agregarRecord("Lesly", 80, 187);
		records.agregarRecord("Moskov", 80, 235);
		records.agregarRecord("Kufra", 80, 306);
		
		esperado = new Records();
		esperado.agregarRecord("Lesly", 80, 187);
		esperado.agregarRecord("Aldous", 80, 199);
		esperado.agregarRecord("Esmeralda", 80, 200);
		esperado.agregarRecord("Nana", 80, 201);
		esperado.agregarRecord("Eudora", 80, 205);
		esperado.agregarRecord("Karina", 80, 215);
		esperado.agregarRecord("Moskov", 80, 235);
		esperado.agregarRecord("Fanny", 80, 250);
		esperado.agregarRecord("Kufra", 80, 306);
		
	}
	
	@Test (expected = RuntimeException.class)
	public void recordLlenoTest()
	{
		records.agregarRecord("Grock", 80, 300);
		records.agregarRecord("karina", 80, 500);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerNombreDePosicionNegativaTest()
	{
		records.obtenerMovimientosDeRecord(-1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerNombreDePosicionInexistenteTest()
	{
		records.obtenerMovimientosDeRecord(10);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerNivelDePosicionNegativaTest()
	{
		records.obtenerNivelDeRecord(-1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerNivelDePosicionInexistenteTest()
	{
		records.obtenerNivelDeRecord(10);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerMovimientosDePosicionNegativaTest()
	{
		records.obtenerMovimientosDeRecord(-1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void obtenerMovimientosDePosicionInexistenteTest()
	{
		records.obtenerMovimientosDeRecord(10);
	}
	
	@Test
	public void obtenerNombreTest()
	{
		String esperado = "Kufra";
		assertEquals(records.obtenerNombreDeRecord(8), esperado);
	}
	
	@Test
	public void obtenerNivelTest()
	{
		assertEquals(records.obtenerNivelDeRecord(0), 80);
	}
	
	@Test
	public void obtenerMovimientosTest()
	{
		assertEquals(records.obtenerMovimientosDeRecord(4), 201);
	}
	
	@Test
	public void ordenarRecordsTest()
	{
		records.ordenarRecords();
		assertEquals(records.toString(), esperado.toString());
	}
	
	@Test
	public void analizarRecordNuevoQueQuedaFueraTest()
	{
		records.agregarRecord("Grock", 80, 300);
		records.analizarRecordNuevo("Karina", 500);
		assertEquals(records.obtenerNombreDeRecord(9), "Kufra");
		
	}
	
	@Test 
	public void analizarRecordNuevoQueQuedaDentroTest()
	{
		records.agregarRecord("Grock", 80, 300);
		records.analizarRecordNuevo("Kimmy", 168);
		assertEquals(records.obtenerNombreDeRecord(0), "Kimmy");
	}
	
	@Test
	public void analizarRecordNuevoSiNoestaLlenoTest()
	{
		records.analizarRecordNuevo("Grock", 300);
		assertEquals(records.obtenerNombreDeRecord(8), "Grock");
	}
	
	@Test
	public void analizaRecordNuevoTest()
	{
		records.agregarRecord("Grock", 80, 300);
		records.analizarRecordNuevo("Karina", 303);
		assertEquals(records.obtenerNombreDeRecord(9), "Karina");
	}
}
