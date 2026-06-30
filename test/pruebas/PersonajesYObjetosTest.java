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
	public void personajeQuedaEliminadoCuandoSuVidaLlegaACero() {
		// Arrange: se prepara un estudiante con pocos puntos de vida.
		Estudiante estudiante = new Estudiante("Neville", 4, 20, List.of(new Expelliarmus()));

		// Act: el estudiante recibe mas danio que su vida actual.
		estudiante.herir(50);

		// Assert: la vida no baja de cero y el personaje deja de estar vivo.
		assertEquals(0, estudiante.getPuntosDeVida());
		assertFalse(estudiante.estaVivo());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void varitaPotenciadoraAumentaElDanioDeHechizosOfensivos() {
		// Arrange: se prepara un auror con varita y un objetivo con 100 puntos de vida.
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Kingsley", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Goyle", 1, 100, List.of(expelliarmus));
		auror.equiparObjeto(new VaritaPotenciadora());

		// Act: el auror lanza Expelliarmus con la varita equipada.
		expelliarmus.ejecutar(auror, objetivo);

		// Assert: el danio aumenta 10 puntos por el objeto magico.
		assertEquals(34, objetivo.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void capaDeInvisibilidadEsquivaSoloElPrimerAtaque() {
		// Arrange: se prepara un objetivo con capa y un atacante con un hechizo ofensivo.
		Expelliarmus expelliarmus = new Expelliarmus();
		Estudiante objetivo = new Estudiante("Luna", 4, 85, List.of(expelliarmus));
		Auror atacante = new Auror("Dawlish", 8, 120, List.of(expelliarmus));
		objetivo.equiparObjeto(new CapaDeInvisibilidad());

		// Act: el atacante intenta daniar dos veces al mismo objetivo.
		expelliarmus.ejecutar(atacante, objetivo);
		expelliarmus.ejecutar(atacante, objetivo);

		// Assert: el primer ataque se esquiva y el segundo descuenta vida.
		assertEquals(29, objetivo.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void amuletoDeRecuperacionAumentaLaCuracion() {
		// Arrange: se prepara un profesor herido con un amuleto de recuperacion.
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Profesor profesor = new Profesor("Flitwick", 10, 110, List.of(expectoPatronum));
		profesor.equiparObjeto(new AmuletoDeRecuperacion());
		profesor.herir(60);

		// Act: el profesor se cura con Expecto Patronum.
		expectoPatronum.ejecutar(profesor, profesor);

		// Assert: la curacion incluye el bonus del profesor y el bonus del amuleto.
		assertEquals(105, profesor.getPuntosDeVida());
		assertTrue(profesor.estaVivo());

		// Anihilate: no hay recursos externos que liberar.
	}
}
