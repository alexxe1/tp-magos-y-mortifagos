package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import hechizos.Protego;
import personajes.magos.Auror;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

public class HechizosTest {

	@Test
	public void expelliarmusDaniaAlObjetivoSegunElPolimorfismoDelLanzador() {
		// Arrange: se prepara un auror con nivel de magia 8 y un objetivo con 100 puntos de vida.
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Tonks", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Crabbe", 1, 100, List.of(expelliarmus));

		// Act: el auror lanza Expelliarmus sobre el seguidor.
		expelliarmus.ejecutar(auror, objetivo);

		// Assert: el objetivo pierde 56 puntos de vida por potencia, magia y bonificaciones del auror.
		assertEquals(44, objetivo.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void avadaKedavraDaniaAlObjetivoSegunElPolimorfismoDelMortifago() {
		// Arrange: se prepara un comandante con nivel de magia 10 y un objetivo con 100 puntos de vida.
		AvadaKedavra avadaKedavra = new AvadaKedavra();
		Comandante comandante = new Comandante("Bellatrix", 10, 120, List.of(avadaKedavra));
		Seguidor objetivo = new Seguidor("Avery", 1, 100, List.of(avadaKedavra));

		// Act: el comandante lanza Avada Kedavra sobre el objetivo.
		avadaKedavra.ejecutar(comandante, objetivo);

		// Assert: el objetivo pierde 75 puntos de vida por potencia, magia y bonificaciones del comandante.
		assertEquals(25, objetivo.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void protegoEvitaElDanioHastaQueSeLimpiaLaRonda() {
		// Arrange: se prepara un objetivo, un atacante y los hechizos de defensa y ataque.
		Protego protego = new Protego();
		Expelliarmus expelliarmus = new Expelliarmus();
		Profesor objetivo = new Profesor("McGonagall", 10, 110, List.of(protego));
		Auror atacante = new Auror("Moody", 8, 120, List.of(expelliarmus));

		// Act: el objetivo se protege, recibe un ataque y luego se limpia el estado de ronda.
		protego.ejecutar(objetivo, atacante);
		expelliarmus.ejecutar(atacante, objetivo);
		objetivo.limpiarEstadoRonda();
		expelliarmus.ejecutar(atacante, objetivo);

		// Assert: el primer ataque no lastima y el segundo, luego de limpiar la ronda, si descuenta vida.
		assertEquals(54, objetivo.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void expectoPatronumCuraSinSuperarLaVidaMaxima() {
		// Arrange: se prepara un profesor herido y un hechizo de curacion.
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Profesor profesor = new Profesor("Lupin", 10, 110, List.of(expectoPatronum));
		profesor.herir(10);

		// Act: el profesor lanza Expecto Patronum.
		expectoPatronum.ejecutar(profesor, profesor);

		// Assert: la curacion no supera los puntos de vida maximos del personaje.
		assertEquals(110, profesor.getPuntosDeVida());

		// Anihilate: no hay recursos externos que liberar.
	}
}
