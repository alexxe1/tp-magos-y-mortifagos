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
	public void hechiceriaCreaCadaTipoDeHechizoCentralizadamente() {
		// Arrange: se preparan los tipos de hechizo disponibles en la fabrica.
		TipoHechizo expelliarmus = TipoHechizo.EXPELLIARMUS;
		TipoHechizo avadaKedavra = TipoHechizo.AVADA_KEDAVRA;
		TipoHechizo protego = TipoHechizo.PROTEGO;
		TipoHechizo expectoPatronum = TipoHechizo.EXPECTO_PATRONUM;

		// Act: se solicita a Hechiceria la creacion de cada hechizo.
		Object hechizoDeAtaqueMago = Hechiceria.crearHechizo(expelliarmus);
		Object hechizoOscuro = Hechiceria.crearHechizo(avadaKedavra);
		Object hechizoDefensivo = Hechiceria.crearHechizo(protego);
		Object hechizoCurativo = Hechiceria.crearHechizo(expectoPatronum);

		// Assert: cada tipo se materializa en su clase concreta correspondiente.
		assertInstanceOf(Expelliarmus.class, hechizoDeAtaqueMago);
		assertInstanceOf(AvadaKedavra.class, hechizoOscuro);
		assertInstanceOf(Protego.class, hechizoDefensivo);
		assertInstanceOf(ExpectoPatronum.class, hechizoCurativo);

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void tiendaDeObjetosCreaCadaObjetoMagicoCentralizadamente() {
		// Arrange: se preparan los tipos de objetos magicos disponibles.
		TipoObjeto varita = TipoObjeto.VARITA;
		TipoObjeto capa = TipoObjeto.CAPA;
		TipoObjeto amuleto = TipoObjeto.AMULETO;

		// Act: se solicita a la tienda la creacion de cada objeto.
		Object objetoDeDanio = TiendaDeObjetos.crear(varita);
		Object objetoDeEsquiva = TiendaDeObjetos.crear(capa);
		Object objetoDeCuracion = TiendaDeObjetos.crear(amuleto);

		// Assert: cada tipo se materializa en su clase concreta correspondiente.
		assertInstanceOf(VaritaPotenciadora.class, objetoDeDanio);
		assertInstanceOf(CapaDeInvisibilidad.class, objetoDeEsquiva);
		assertInstanceOf(AmuletoDeRecuperacion.class, objetoDeCuracion);

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void reclutadorCreaMagosConHechizosIniciales() {
		// Arrange: se prepara el pedido centralizado de un mago sin conocer su subclase concreta.

		// Act: se crea un mago mediante el reclutador.
		Mago mago = Reclutador.crearMago();

		// Assert: el mago queda armado con los tres hechizos iniciales esperados para su faccion.
		assertEquals(3, mago.getHechizosParaLanzar().size());
		assertTrue(mago.getHechizosParaLanzar().stream().anyMatch(Expelliarmus.class::isInstance));
		assertTrue(mago.getHechizosParaLanzar().stream().anyMatch(Protego.class::isInstance));
		assertTrue(mago.getHechizosParaLanzar().stream().anyMatch(ExpectoPatronum.class::isInstance));

		// Anihilate: no hay recursos externos que liberar.
	}

	@Test
	public void reclutadorCreaMortifagosConHechizosIniciales() {
		// Arrange: se prepara el pedido centralizado de un mortifago sin conocer su subclase concreta.

		// Act: se crea un mortifago mediante el reclutador.
		Mortifago mortifago = Reclutador.crearMortifago();

		// Assert: el mortifago queda armado con los tres hechizos iniciales esperados para su faccion.
		assertEquals(3, mortifago.getHechizosParaLanzar().size());
		assertTrue(mortifago.getHechizosParaLanzar().stream().anyMatch(AvadaKedavra.class::isInstance));
		assertTrue(mortifago.getHechizosParaLanzar().stream().anyMatch(Protego.class::isInstance));
		assertTrue(mortifago.getHechizosParaLanzar().stream().anyMatch(ExpectoPatronum.class::isInstance));

		// Anihilate: no hay recursos externos que liberar.
	}
}
