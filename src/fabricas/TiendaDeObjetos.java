package fabricas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import objetos.AmuletoDeRecuperacion;
import objetos.CapaDeInvisibilidad;
import objetos.ObjetoMagico;
import objetos.TipoObjeto;
import objetos.VaritaPotenciadora;

public class TiendaDeObjetos {

	private static Random random = new Random();
	private static final int MAX_OBJETOS_ALEATORIOS = 3;

	public static ObjetoMagico crear(TipoObjeto tipo) {

		switch (tipo) {

		case TipoObjeto.VARITA:
			return new VaritaPotenciadora();

		case TipoObjeto.CAPA:
			return new CapaDeInvisibilidad();

		case TipoObjeto.AMULETO:
			return new AmuletoDeRecuperacion();
		}

		throw new IllegalArgumentException("Tipo inválido");
	}

	// Devuelve una lista con objetos mágicos aleatorios de 0 a
	// MAX_OBJETOS_ALEATORIOS
	public static List<ObjetoMagico> generarObjetosAleatorios() {

		int cantidad = random.nextInt(MAX_OBJETOS_ALEATORIOS + 1);

		List<ObjetoMagico> objetos = new ArrayList<>();

		TipoObjeto[] tipos = TipoObjeto.values();

		for (int i = 0; i < cantidad; i++) {

			TipoObjeto tipoAleatorio = tipos[random.nextInt(tipos.length)];

			objetos.add(TiendaDeObjetos.crear(tipoAleatorio));
		}

		return objetos;
	}
}
