package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.Test;
import baseDeDatos.Datos;

public class DatosTest {
	
	@Test
	public void obtenerNivelTest() throws IOException
	{
		String esperado = "11011101010111010101110115";
		
		String nivel = Datos.obtenerNivel(2);
		
		assertEquals(esperado, nivel);
	}
	
	@Test 
	public void meQuedeSinNivelesTest() throws IOException 
	{
		String nivel = Datos.obtenerNivel(30);
		
		assertEquals(nivel, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void nivelNegativoTest() throws IOException
	{
		Datos.obtenerNivel(-1);
	}
	
}
