package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import batalla.Batallon;
import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import hechizos.Hechizo;
import objetos.VaritaPotenciadora;
import personajes.Personaje;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
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

	@Test
	public void hechizoNoOfensivoSeAplicaSobreElPropioBatallonSinDaniarAlEnemigo() {
		// Arrange: se prepara un profesor herido que solo puede curarse y un enemigo vivo.
		Batallon magos = new Batallon();
		Batallon mortifagos = new Batallon();
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Profesor profesor = new Profesor("Lupin", 10, 110, List.of(expectoPatronum));
		Seguidor enemigo = new Seguidor("Avery", 5, 75, List.of(new Expelliarmus()));
		profesor.herir(40);
		magos.agregarPersonaje(profesor);
		mortifagos.agregarPersonaje(enemigo);

		// Act: el batallon de magos ejecuta su turno con un hechizo no ofensivo.
		magos.atacar(mortifagos);

		// Assert: el profesor se cura y el enemigo no recibe danio.
		assertEquals(110, profesor.getPuntosDeVida());
		assertEquals(75, enemigo.getPuntosDeVida());
		assertEquals(1, magos.getHistorialHechizos().get(profesor).size());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void objetoEquipadoYQuitadoDuranteBatallaCambiaElDanioDelSiguienteTurno() {
		// Arrange: se prepara un auror con una varita contra un objetivo con mucha vida.
		Batallon magos = new Batallon();
		Batallon mortifagos = new Batallon();
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Tonks", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Goyle", 5, 200, List.of(expelliarmus));
		VaritaPotenciadora varita = new VaritaPotenciadora();
		auror.equiparObjeto(varita);
		magos.agregarPersonaje(auror);
		mortifagos.agregarPersonaje(objetivo);

		// Act: ataca con la varita equipada, luego se la quita y vuelve a atacar.
		magos.atacar(mortifagos);
		auror.quitarObjeto(varita);
		magos.atacar(mortifagos);

		// Assert: el primer ataque usa la varita y el segundo ya no suma su bonificacion.
		assertEquals(78, objetivo.getPuntosDeVida());
		assertEquals(2, magos.getHistorialHechizos().get(auror).size());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void gettersDeBatallonNoPermitenMutarSuEstadoInterno() {
		// Arrange: se prepara un batallon con un unico personaje.
		Batallon batallon = new Batallon();
		Estudiante estudiante = new Estudiante("Harry", 4, 85, List.of(new Expelliarmus()));
		batallon.agregarPersonaje(estudiante);

		// Act: se intentan modificar las colecciones devueltas por los getters.
		List<Personaje> personajes = batallon.getPersonajes();
		Map<Personaje, List<Hechizo>> historial = batallon.getHistorialHechizos();

		// Assert: ninguna coleccion externa puede alterar el estado interno del batallon.
		assertThrows(UnsupportedOperationException.class, () -> personajes.clear());
		assertThrows(UnsupportedOperationException.class, () -> historial.clear());
		assertThrows(UnsupportedOperationException.class, () -> historial.get(estudiante).add(new Expelliarmus()));
		assertEquals(1, batallon.getPersonajes().size());
		assertTrue(batallon.getHistorialHechizos().get(estudiante).isEmpty());

		// Anihilate: no hay recursos externos que liberar.
	}
}
