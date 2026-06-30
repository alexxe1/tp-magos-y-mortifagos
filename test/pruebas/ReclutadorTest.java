package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fabricas.Reclutador;

public class ReclutadorTest {

	@Test
	public void testNombreGenericoCuandoListaVacia() {
		List<String> nombres = new ArrayList<>(List.of("Kingsley"));

		String primerNombre = Reclutador.elegirNombre(nombres, "Auror");
		String segundoNombre = Reclutador.elegirNombre(nombres, "Auror");

		assertEquals("Kingsley", primerNombre);
		assertEquals("Auror", segundoNombre);
		assertEquals(0, nombres.size());
	}
}