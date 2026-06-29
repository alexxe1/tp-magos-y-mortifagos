package personajes.mortifagos;

import java.util.List;

import hechizos.Hechizo;
import personajes.Personaje;

public abstract class Mortifago extends Personaje {
	protected Mortifago(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
	}

	@Override
	public int calcularDaño(Hechizo hechizo) {
		return hechizo.getPotenciaBase() + getNivelDeMagia() + 5;
	}

	@Override
	public int calcularCuracion(Hechizo hechizo) {
		return hechizo.getPotenciaBase() + getNivelDeMagia();
	}
}
