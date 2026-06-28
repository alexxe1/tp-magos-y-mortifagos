package hechizos;

import personajes.Personaje;

public class ExpectoPatronum implements Hechizo {
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.curar(lanzador.calcularCuracion(this));
	}

	@Override
	public int getPotenciaBase() {
		return 20;
	}
}
