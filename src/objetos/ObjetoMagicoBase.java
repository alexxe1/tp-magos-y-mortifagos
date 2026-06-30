package objetos;

import hechizos.Hechizo;

public abstract class ObjetoMagicoBase implements ObjetoMagico {
	@Override
	public int modificarDaño(Hechizo hechizo, int daño) {
		return daño;
	}

	@Override
	public int modificarCuracion(int curacion) {
		return curacion;
	}

	@Override
	public boolean esquivarAtaque() {
		return false;
	}
}
