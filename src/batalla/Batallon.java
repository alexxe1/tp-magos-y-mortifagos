package batalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hechizos.Hechizo;
import personajes.Personaje;

public class Batallon {
	private List<Personaje> personajes;
	private Map<Personaje, List<Hechizo>> historialHechizos;

	public Batallon() {
		personajes = new ArrayList<>();
		historialHechizos = new HashMap<>();
	}

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
		historialHechizos.put(personaje, new ArrayList<>());
	}

	public boolean tienePersonajesSaludable() {
		for (Personaje personaje : personajes) {
			if (personaje.getPuntosDeVida() > 0) {
				return true;
			}
		}

		return false;
	}

	public void atacar(Batallon batallon) {
		Set<Hechizo> hechizosUsadosEnTurno = new HashSet<>();

		for (Personaje personaje : personajes) {
			if (personaje.getPuntosDeVida() > 0 && batallon.tienePersonajesSaludable()) {
				Hechizo hechizo = elegirHechizoNoRepetido(personaje, hechizosUsadosEnTurno);

				if (hechizo != null) {
					Personaje objetivo = batallon.elegirObjetivo();
					hechizo.ejecutar(personaje, objetivo);
					hechizosUsadosEnTurno.add(hechizo);
					historialHechizos.get(personaje).add(hechizo);
				}
			}
		}
	}

	public Map<Personaje, List<Hechizo>> getHistorialHechizos() {
		return historialHechizos;
	}

	public void limpiarEstadoRonda() {
		for (Personaje personaje : personajes) {
			personaje.limpiarEstadoRonda();
		}
	}

	private Hechizo elegirHechizoNoRepetido(Personaje personaje, Set<Hechizo> hechizosUsadosEnTurno) {
		for (int intento = 0; intento < 10; intento++) {
			try {
				Hechizo hechizo = personaje.elegirHechizo();

				if (!hechizosUsadosEnTurno.contains(hechizo)) {
					return hechizo;
				}
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	private Personaje elegirObjetivo() {
		for (Personaje personaje : personajes) {
			if (personaje.getPuntosDeVida() > 0) {
				return personaje;
			}
		}

		return null;
	}

}
