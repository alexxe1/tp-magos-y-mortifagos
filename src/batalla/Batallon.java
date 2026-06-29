package batalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

				Personaje objetivo = batallon.elegirObjetivo();
				
				if (hechizo != null) {
				    hechizo.ejecutar(personaje, objetivo);
				    hechizosUsadosEnTurno.add(hechizo);
				    historialHechizos.get(personaje).add(hechizo);
				} else {
					// Si da null es porque no quedan hechizos sin repetir y ataca sin hechizo
				    personaje.atacarSinHechizo(objetivo);
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
	    List<Hechizo> disponibles = new ArrayList<>(personaje.getHechizosParaLanzar());
	    
	    disponibles.removeAll(hechizosUsadosEnTurno);
	    
	    if (disponibles.isEmpty()) return null;
	    
	    return disponibles.get(new Random().nextInt(disponibles.size()));
	}

	private Personaje elegirObjetivo() {
	    List<Personaje> vivos = new ArrayList<>();
	    
	    for (Personaje personaje : personajes) {
	        if (personaje.getPuntosDeVida() > 0) {
	            vivos.add(personaje);
	        }
	    }
	    
	    if (vivos.isEmpty()) return null;
	    
	    return vivos.get(new Random().nextInt(vivos.size()));
	}
}
