package hechizos;

import personajes.Personaje;

public class Protego extends HechizoBase {
	private static final int POTENCIA_BASE = 0;
	
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.setEstaProtegido(true);
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
