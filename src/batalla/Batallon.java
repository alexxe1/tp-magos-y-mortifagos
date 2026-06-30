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
	private List<Personaje> personajes = new ArrayList<>();
	private Map<Personaje, List<Hechizo>> historialHechizos = new HashMap<>();

	public void agregarPersonaje(Personaje personaje) {
		personajes.add(personaje);
		historialHechizos.put(personaje, new ArrayList<>());
	}

	public boolean tienePersonajesSaludables() {
		for (Personaje personaje : personajes) {
			if (personaje.estaVivo()) {
				return true;
			}
		}

		return false;
	}

	public void atacar(Batallon batallonEnemigo) {
	    Set<Hechizo> hechizosUsadosEnTurno = new HashSet<>();

	    for (Personaje personaje : personajes) {
	    	
	    	if (!personaje.estaVivo()) continue;
	    	if (!batallonEnemigo.tienePersonajesSaludables()) continue;
	    	
	    	Hechizo hechizo = elegirHechizoNoRepetido(personaje, hechizosUsadosEnTurno);

            if (hechizo != null) {
            	
                Personaje objetivo = hechizo.esOfensivo()
                    ? batallonEnemigo.elegirObjetivo()
                    : this.elegirObjetivo();

                hechizo.ejecutar(personaje, objetivo);
                hechizosUsadosEnTurno.add(hechizo);
                historialHechizos.get(personaje).add(hechizo);
                
                System.out.println("  " + personaje.getNombre() + " lanza " + hechizo.getClass().getSimpleName() + " sobre " + objetivo.getNombre());
            } else {
                Personaje objetivo = batallonEnemigo.elegirObjetivo();
                
                personaje.atacarSinHechizo(objetivo);
                
                System.out.println("  " + personaje.getNombre() + " ataca sin hechizo a " + objetivo.getNombre());
            }
	    }
	}

	public Map<Personaje, List<Hechizo>> getHistorialHechizos() {
		return historialHechizos;
	}
	
	public List<Personaje> getPersonajes() {
	    return personajes;
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
	        if (personaje.estaVivo()) {
	            vivos.add(personaje);
	        }
	    }
	    
	    if (vivos.isEmpty()) return null;
	    
	    return vivos.get(new Random().nextInt(vivos.size()));
	}
}
