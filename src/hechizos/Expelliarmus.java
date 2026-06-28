package hechizos;

import personajes.Personaje;

public class Expelliarmus implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.herir(lanzador.calcularDaño(this));
	}

	@Override
	public int getPotenciaBase() {
		return 15;
	}
}
