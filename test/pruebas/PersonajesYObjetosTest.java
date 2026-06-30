package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import objetos.AmuletoDeRecuperacion;
import objetos.CapaDeInvisibilidad;
import objetos.VaritaPotenciadora;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Seguidor;

public class PersonajesYObjetosTest {

	@Test
	public void testPersonajeEliminadoCuandoVidaLlegaACero() {
		Estudiante estudiante = new Estudiante("Neville", 4, 20, List.of(new Expelliarmus()));

		estudiante.herir(50);

		assertEquals(0, estudiante.getPuntosDeVida());
		assertFalse(estudiante.estaVivo());
	}

	@Test
	public void testVaritaAumentaDanioDeHechizosOfensivos() {
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Kingsley", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Goyle", 1, 100, List.of(expelliarmus));
		auror.equiparObjeto(new VaritaPotenciadora());

		expelliarmus.ejecutar(auror, objetivo);

		assertEquals(34, objetivo.getPuntosDeVida());
	}

	@Test
	public void testCapaEsquivaSoloElPrimerAtaque() {
		Expelliarmus expelliarmus = new Expelliarmus();
		Estudiante objetivo = new Estudiante("Luna", 4, 85, List.of(expelliarmus));
		Auror atacante = new Auror("Dawlish", 8, 120, List.of(expelliarmus));
		objetivo.equiparObjeto(new CapaDeInvisibilidad());

		expelliarmus.ejecutar(atacante, objetivo);
		expelliarmus.ejecutar(atacante, objetivo);

		assertEquals(29, objetivo.getPuntosDeVida());
	}

	@Test
	public void testAmuletoAumentaCuracion() {
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Profesor profesor = new Profesor("Flitwick", 10, 110, List.of(expectoPatronum));
		profesor.equiparObjeto(new AmuletoDeRecuperacion());
		profesor.herir(60);

		expectoPatronum.ejecutar(profesor, profesor);

		assertEquals(105, profesor.getPuntosDeVida());
		assertTrue(profesor.estaVivo());
	}

	@Test
	public void testQuitarObjetoDejaDeModificarComportamiento() {
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Moody", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Rookwood", 1, 200, List.of(expelliarmus));
		VaritaPotenciadora varita = new VaritaPotenciadora();
		auror.equiparObjeto(varita);

		expelliarmus.ejecutar(auror, objetivo);
		auror.quitarObjeto(varita);
		expelliarmus.ejecutar(auror, objetivo);

		assertEquals(78, objetivo.getPuntosDeVida());
	}
}