package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import hechizos.AvadaKedavra;
import hechizos.ExpectoPatronum;
import hechizos.Expelliarmus;
import hechizos.Protego;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Seguidor;

public class HechizosTest {

	@Test
	public void testExpelliarmusDañaSegunNivelDeMagiaDelAuror() {
		Expelliarmus expelliarmus = new Expelliarmus();
		Auror auror = new Auror("Tonks", 8, 120, List.of(expelliarmus));
		Seguidor objetivo = new Seguidor("Crabbe", 1, 100, List.of(expelliarmus));

		expelliarmus.ejecutar(auror, objetivo);

		assertEquals(44, objetivo.getPuntosDeVida());
	}

	@Test
	public void testAvadaKedavraDañaSegunNivelDeMagiaDelComandante() {
		AvadaKedavra avadaKedavra = new AvadaKedavra();
		Comandante comandante = new Comandante("Bellatrix", 10, 120, List.of(avadaKedavra));
		Seguidor objetivo = new Seguidor("Avery", 1, 100, List.of(avadaKedavra));

		avadaKedavra.ejecutar(comandante, objetivo);

		assertEquals(25, objetivo.getPuntosDeVida());
	}

	@Test
	public void testProtegoEvitaDanioHastaFinDeRonda() {
		Protego protego = new Protego();
		Expelliarmus expelliarmus = new Expelliarmus();
		Profesor objetivo = new Profesor("McGonagall", 10, 110, List.of(protego));
		Auror atacante = new Auror("Moody", 8, 120, List.of(expelliarmus));

		protego.ejecutar(objetivo, atacante);
		expelliarmus.ejecutar(atacante, objetivo);
		objetivo.limpiarEstadoRonda();
		expelliarmus.ejecutar(atacante, objetivo);

		assertEquals(54, objetivo.getPuntosDeVida());
	}

	@Test
	public void testExpectoPatronumNoCuraMasAllaDelMaximo() {
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Profesor profesor = new Profesor("Lupin", 10, 110, List.of(expectoPatronum));
		profesor.herir(10);

		expectoPatronum.ejecutar(profesor, profesor);

		assertEquals(110, profesor.getPuntosDeVida());
	}

	@Test
	public void testExpectoPatronumCuraSegunNivelDeMagiaDelEstudiante() {
		ExpectoPatronum expectoPatronum = new ExpectoPatronum();
		Estudiante estudiante = new Estudiante("Hermione", 4, 85, List.of(expectoPatronum));
		estudiante.herir(50);

		expectoPatronum.ejecutar(estudiante, estudiante);

		assertEquals(74, estudiante.getPuntosDeVida());
	}

	@Test
	public void testMortifagoConExpelliarmusNoBonificaDañoOscuro() {
		Expelliarmus expelliarmus = new Expelliarmus();
		Seguidor seguidor = new Seguidor("Yaxley", 6, 100, List.of(expelliarmus));
		Estudiante objetivo = new Estudiante("Dean", 1, 100, List.of(expelliarmus));

		expelliarmus.ejecutar(seguidor, objetivo);

		assertEquals(59, objetivo.getPuntosDeVida());
	}
}