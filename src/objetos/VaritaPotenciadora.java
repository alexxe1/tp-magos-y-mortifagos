package objetos;

import hechizos.Hechizo;

public class VaritaPotenciadora extends ObjetoMagicoBase {
	
	private static final int DAÑO_EXTRA = 10;
	
	@Override
	public String getNombre() {
		return "Varita potenciadora";
	}

	@Override
	public int modificarDaño(Hechizo hechizo, int daño) {
		if (hechizo.esOfensivo()) {
			return daño + DAÑO_EXTRA;
		}

		return daño;
	}
}
