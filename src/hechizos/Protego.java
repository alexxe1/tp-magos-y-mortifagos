package hechizos;

import personajes.Personaje;

public class Protego extends HechizoBase {

	@Override
	public void ejecutar(Personaje lanzador, Personaje objetivo) {
		lanzador.setEstaProtegido(true);
	}

	@Override
	public int getPotenciaBase() {
		return 0;
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
