package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fabricas.Hechiceria;
import fabricas.Reclutador;
import fabricas.TiendaDeObjetos;
import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.ExpectoPatronum;
import hechizos.Hechizo;
import hechizos.Protego;
import hechizos.TipoHechizo;
import objetos.AmuletoDeRecuperacion;
import objetos.CapaDeInvisibilidad;
import objetos.TipoObjeto;
import objetos.VaritaPotenciadora;
import personajes.magos.Mago;
import personajes.mortifagos.Mortifago;

public class FabricasTest {

	@Test
	public void testHechiceriaCreaCadaTipoDeHechizo() {
		assertInstanceOf(Expelliarmus.class, Hechiceria.crearHechizo(TipoHechizo.EXPELLIARMUS));
		assertInstanceOf(AvadaKedavra.class, Hechiceria.crearHechizo(TipoHechizo.AVADA_KEDAVRA));
		assertInstanceOf(Protego.class, Hechiceria.crearHechizo(TipoHechizo.PROTEGO));
		assertInstanceOf(ExpectoPatronum.class, Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
	}

	@Test
	public void testTiendaCreaCadaTipoDeObjeto() {
		assertInstanceOf(VaritaPotenciadora.class, TiendaDeObjetos.crear(TipoObjeto.VARITA));
		assertInstanceOf(CapaDeInvisibilidad.class, TiendaDeObjetos.crear(TipoObjeto.CAPA));
		assertInstanceOf(AmuletoDeRecuperacion.class, TiendaDeObjetos.crear(TipoObjeto.AMULETO));
	}

	@Test
	public void testReclutadorCreaMagoConHechizosIniciales() {
		Mago mago = Reclutador.crearMago();

		assertEquals(3, mago.getHechizosParaLanzar().size());

		boolean tieneExpelliarmus = false;
		boolean tieneProtego = false;
		boolean tieneExpectoPatronum = false;

		for (Hechizo hechizo : mago.getHechizosParaLanzar()) {
			if (hechizo instanceof Expelliarmus) tieneExpelliarmus = true;
			if (hechizo instanceof Protego) tieneProtego = true;
			if (hechizo instanceof ExpectoPatronum) tieneExpectoPatronum = true;
		}

		assertTrue(tieneExpelliarmus);
		assertTrue(tieneProtego);
		assertTrue(tieneExpectoPatronum);
	}

	@Test
	public void testReclutadorCreaMortifagoConHechizosIniciales() {
		Mortifago mortifago = Reclutador.crearMortifago();

		assertEquals(3, mortifago.getHechizosParaLanzar().size());

		boolean tieneAvadaKedavra = false;
		boolean tieneProtego = false;
		boolean tieneExpectoPatronum = false;

		for (Hechizo hechizo : mortifago.getHechizosParaLanzar()) {
			if (hechizo instanceof AvadaKedavra) tieneAvadaKedavra = true;
			if (hechizo instanceof Protego) tieneProtego = true;
			if (hechizo instanceof ExpectoPatronum) tieneExpectoPatronum = true;
		}

		assertTrue(tieneAvadaKedavra);
		assertTrue(tieneProtego);
		assertTrue(tieneExpectoPatronum);
	}
}