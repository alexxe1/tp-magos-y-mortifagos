package personajes.mortifagos;

import java.util.List;

import hechizos.Hechizo;

public class Seguidor extends Mortifago {
	public Seguidor(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
	}
}
