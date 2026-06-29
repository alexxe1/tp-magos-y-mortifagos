package hechizos;

import personajes.Personaje;

public interface Hechizo {
	public void ejecutar(Personaje lanzador, Personaje objetivo);
    
	public int getPotenciaBase();
	boolean esOfensivo();
}
