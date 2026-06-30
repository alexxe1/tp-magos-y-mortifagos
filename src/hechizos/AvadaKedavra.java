package hechizos;

import personajes.Personaje;

public class AvadaKedavra extends HechizoBase {
	private static final int POTENCIA_BASE = 45;
	
	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		objetivo.herir(lanzador.calcularDaño(this));
	}

	@Override
	public int getPotenciaBase() {
		return POTENCIA_BASE;
	}

	@Override
	public boolean esOfensivo() {
		return true;
	}

	@Override
	public boolean esOscuro() {
		return true;
	}
}
