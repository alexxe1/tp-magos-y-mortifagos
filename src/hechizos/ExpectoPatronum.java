package hechizos;

import personajes.Personaje;

public class ExpectoPatronum extends HechizoBase {
	private static final int POTENCIA_BASE = 20;
	
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.curar(lanzador.calcularCuracion(this));
	}

	@Override
	public int getPotenciaBase() {
		return POTENCIA_BASE;
	}
	
	@Override
	public boolean esOfensivo() {
		return false;
	}

	@Override
	public boolean esOscuro() {
		return false;
	}
}
