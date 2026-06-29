package objetos;

import hechizos.Hechizo;

public class VaritaPotenciadora extends ObjetoMagicoBase {
	@Override
	public String getNombre() {
		return "Varita potenciadora";
	}

	@Override
	public int modificarDaño(Hechizo hechizo, int daño) {
		if (hechizo.esOfensivo()) {
			return daño + 10;
		}

		return daño;
	}
}
