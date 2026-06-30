package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ReclutadorTest {

	@Test
	public void elegirNombreUsaNombreGenericoCuandoSeAgotanLosNombresDisponibles() {
		// Arrange: se prepara una lista con un unico nombre disponible.
		List<String> nombres = new ArrayList<>(List.of("Kingsley"));

		// Act: se elige una vez desde la lista y luego se vuelve a pedir otro nombre.
		String primerNombre = Reclutador.elegirNombre(nombres, "Auror");
		String nombreAgotado = Reclutador.elegirNombre(nombres, "Auror");

		// Assert: el primer pedido consume el nombre real y el segundo usa el generico.
		assertEquals("Kingsley", primerNombre);
		assertEquals("Auror", nombreAgotado);
		assertEquals(0, nombres.size());

		// Anihilate: no hay recursos externos que liberar.
	}
}
