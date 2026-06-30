package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import batalla.Batallon;
import hechizos.Expelliarmus;
import personajes.Personaje;
import personajes.magos.Estudiante;
import personajes.mortifagos.Seguidor;

public class BatallonTest {

	@Test
	public void agregarPersonajeMantieneOrdenYRegistraHistorialVacio() {
		// Arrange: se prepara un batallon y dos personajes para agregar.
		Batallon batallon = new Batallon();
		Estudiante primero = new Estudiante("Harry", 4, 85, List.of(new Expelliarmus()));
		Estudiante segundo = new Estudiante("Hermione", 4, 85, List.of(new Expelliarmus()));

		// Act: se agregan ambos personajes al batallon.
		batallon.agregarPersonaje(primero);
		batallon.agregarPersonaje(segundo);

		// Assert: la lista conserva la secuencia y el mapa crea historial para cada personaje.
		assertSame(primero, batallon.getPersonajes().get(0));
		assertSame(segundo, batallon.getPersonajes().get(1));
		assertTrue(batallon.getHistorialHechizos().containsKey(primero));
		assertTrue(batallon.getHistorialHechizos().containsKey(segundo));
		assertTrue(batallon.getHistorialHechizos().get(primero).isEmpty());
		assertTrue(batallon.getHistorialHechizos().get(segundo).isEmpty());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void batallonNoRepiteUnMismoHechizoDentroDelTurno() {
		// Arrange: se prepara un batallon con dos integrantes que solo conocen Expelliarmus.
		Batallon atacantes = new Batallon();
		Batallon defensores = new Batallon();
		atacantes.agregarPersonaje(new Estudiante("Harry", 4, 85, List.of(new Expelliarmus())));
		atacantes.agregarPersonaje(new Estudiante("Ron", 4, 85, List.of(new Expelliarmus())));
		defensores.agregarPersonaje(new Seguidor("Avery", 1, 200, List.of(new Expelliarmus())));

		// Act: el batallon atacante realiza su turno completo.
		atacantes.atacar(defensores);

		// Assert: solo queda registrado un Expelliarmus porque el Set evita repetirlo en el turno.
		long hechizosRegistrados = atacantes.getHistorialHechizos().values().stream()
				.flatMap(List::stream)
				.filter(hechizo -> hechizo instanceof Expelliarmus)
				.count();
		assertEquals(1, hechizosRegistrados);

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void personajesEliminadosNoRealizanAccionesEnElTurno() {
		// Arrange: se prepara un batallon con un personaje eliminado y otro vivo.
		Batallon atacantes = new Batallon();
		Batallon defensores = new Batallon();
		Estudiante eliminado = new Estudiante("Neville", 4, 85, List.of(new Expelliarmus()));
		Estudiante vivo = new Estudiante("Ginny", 4, 85, List.of(new Expelliarmus()));
		Seguidor objetivo = new Seguidor("Rookwood", 1, 200, List.of(new Expelliarmus()));
		eliminado.herir(200);
		atacantes.agregarPersonaje(eliminado);
		atacantes.agregarPersonaje(vivo);
		defensores.agregarPersonaje(objetivo);

		// Act: el batallon atacante realiza su turno.
		atacantes.atacar(defensores);

		// Assert: el personaje eliminado no registra hechizos y el personaje vivo si puede actuar.
		assertTrue(atacantes.getHistorialHechizos().get(eliminado).isEmpty());
		assertEquals(1, atacantes.getHistorialHechizos().get(vivo).size());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void tienePersonajesSaludablesIndicaSiQuedaAlguienEnPie() {
		// Arrange: se prepara un batallon con un unico personaje.
		Batallon batallon = new Batallon();
		Personaje personaje = new Estudiante("Draco", 4, 85, List.of(new Expelliarmus()));
		batallon.agregarPersonaje(personaje);

		// Act: se elimina al unico personaje del batallon.
		personaje.herir(200);

		// Assert: el batallon informa que no tiene personajes saludables.
		assertFalse(batallon.tienePersonajesSaludables());

		// Anihilate: no hay recursos externos que liberar.
	}
}
