package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import batalla.Batallon;
import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import hechizos.Hechizo;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Seguidor;
import objetos.VaritaPotenciadora;

public class BatallonTest {

	@Test
	public void testAgregarPersonajeMantieneOrden() {
		Batallon batallon = new Batallon();
		Estudiante primero = new Estudiante("Harry", 4, 85, List.of(new Expelliarmus()));
		Estudiante segundo = new Estudiante("Hermione", 4, 85, List.of(new Expelliarmus()));

		batallon.agregarPersonaje(primero);
		batallon.agregarPersonaje(segundo);

		assertSame(primero, batallon.getPersonajes().get(0));
		assertSame(segundo, batallon.getPersonajes().get(1));
		assertTrue(batallon.getHistorialHechizos().get(primero).isEmpty());
		assertTrue(batallon.getHistorialHechizos().get(segundo).isEmpty());
	}

	@Test
	public void testBatallonNoRepiteHechizosEnMismoTurno() {
		Batallon atacantes = new Batallon();
		Batallon defensores = new Batallon();
		atacantes.agregarPersonaje(new Estudiante("Harry", 4, 85, List.of(new Expelliarmus())));
		atacantes.agregarPersonaje(new Estudiante("Ron", 4, 85, List.of(new Expelliarmus())));
		defensores.agregarPersonaje(new Seguidor("Avery", 1, 200, List.of(new Expelliarmus())));

		atacantes.atacar(defensores);

		int hechizosRegistrados = 0;
		for (List<Hechizo> hechizos : atacantes.getHistorialHechizos().values()) {
			hechizosRegistrados += hechizos.size();
		}
		assertEquals(1, hechizosRegistrados);
	}

	@Test
	public void testPersonajeEliminadoNoAtaca() {
		Batallon atacantes = new Batallon();
		Batallon defensores = new Batallon();
		Estudiante eliminado = new Estudiante("Neville", 4, 85, List.of(new Expelliarmus()));
		Estudiante vivo = new Estudiante("Ginny", 4, 85, List.of(new Expelliarmus()));
		
		eliminado.herir(200);
		atacantes.agregarPersonaje(eliminado);
		atacantes.agregarPersonaje(vivo);
		defensores.agregarPersonaje(new Seguidor("Rookwood", 1, 200, List.of(new Expelliarmus())));

		atacantes.atacar(defensores);

		assertTrue(atacantes.getHistorialHechizos().get(eliminado).isEmpty());
		assertEquals(1, atacantes.getHistorialHechizos().get(vivo).size());
	}

	@Test
	public void testBatallonSinVivosNotienePersonajesSaludables() {
		Batallon batallon = new Batallon();
		Estudiante personaje = new Estudiante("Draco", 4, 85, List.of(new Expelliarmus()));
		batallon.agregarPersonaje(personaje);

		personaje.herir(200);

		assertFalse(batallon.tienePersonajesSaludables());
	}

	@Test
	public void testHechizoNoOfensivoNoDaniaAlEnemigo() {
		Batallon magos = new Batallon();
		Batallon mortifagos = new Batallon();
		Profesor profesor = new Profesor("Lupin", 10, 110, List.of(new ExpectoPatronum()));
		Seguidor enemigo = new Seguidor("Avery", 5, 75, List.of(new Expelliarmus()));
		
		profesor.herir(40);
		magos.agregarPersonaje(profesor);
		mortifagos.agregarPersonaje(enemigo);

		magos.atacar(mortifagos);

		assertEquals(110, profesor.getPuntosDeVida());
		assertEquals(75, enemigo.getPuntosDeVida());
	}

	@Test
	public void testQuitarObjetoCambiaElDanioEnSiguienteTurno() {
		Batallon magos = new Batallon();
		Batallon mortifagos = new Batallon();
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Tonks", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Goyle", 5, 200, List.of(expelliarmus));
		VaritaPotenciadora varita = new VaritaPotenciadora();
		
		auror.equiparObjeto(varita);
		magos.agregarPersonaje(auror);
		mortifagos.agregarPersonaje(objetivo);

		magos.atacar(mortifagos);
		auror.quitarObjeto(varita);
		magos.atacar(mortifagos);

		assertEquals(78, objetivo.getPuntosDeVida());
	}
}