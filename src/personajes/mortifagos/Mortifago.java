package personajes.mortifagos;

import java.util.List;

import hechizos.Hechizo;
import personajes.Personaje;

public abstract class Mortifago extends Personaje {
	private static final int SIN_BONIFICACION = 0;
	private static final int BONIFICACION_DAÑO_MORTIFAGO = 5;
	
	protected Mortifago(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
	}

	@Override
	public int calcularDaño(Hechizo hechizo) {
		int bonificacionOscura = hechizo.esOscuro() ? BONIFICACION_DAÑO_MORTIFAGO : SIN_BONIFICACION;

		return aplicarObjetosAlDaño(hechizo, hechizo.getPotenciaBase() + getNivelDeMagia() + bonificacionOscura);
	}

	@Override
	public int calcularCuracion(Hechizo hechizo) {
		return aplicarObjetosALaCuracion(hechizo.getPotenciaBase() + getNivelDeMagia());
	}
}
