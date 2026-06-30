package objetos;

import hechizos.Hechizo;

public interface ObjetoMagico {
	String getNombre();

	int modificarDaño(Hechizo hechizo, int daño);

	int modificarCuracion(int curacion);
	
	boolean esquivarAtaque();
}
