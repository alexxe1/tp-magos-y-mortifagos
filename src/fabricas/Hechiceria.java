package fabricas;

import hechizos.AvadaKedavra;
import hechizos.Expelliarmus;
import hechizos.ExpectoPatronum;
import hechizos.Hechizo;
import hechizos.Protego;
import hechizos.TipoHechizo;

public class Hechiceria {
	public static Hechizo crearHechizo(TipoHechizo tipo) {
		switch (tipo) {
			case EXPELLIARMUS:
				return new Expelliarmus();
			case AVADA_KEDAVRA:
				return new AvadaKedavra();
			case PROTEGO:
				return new Protego();
			case EXPECTO_PATRONUM:
				return new ExpectoPatronum();
			default:
				throw new IllegalArgumentException("Tipo de hechizo desconocido: " + tipo);
		}
	}
}
