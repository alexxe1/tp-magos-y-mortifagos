package hechizos;

import personajes.Personaje;

public class Protego implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.setEstaProtegido(true);
	}

	@Override
	public int getPotenciaBase() {
		return 0;
	}
}
