package personajes.mortifagos;

import java.util.List;

import hechizos.Hechizo;

public class Comandante extends Mortifago {
	private static final int BONIFICACION_DANIO_COMANDANTE = 15;
	
	public Comandante(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
	}

	@Override
	public int calcularDaño(Hechizo hechizo) {
		return super.calcularDaño(hechizo) + BONIFICACION_DANIO_COMANDANTE;
	}
}
