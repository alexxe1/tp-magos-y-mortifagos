package juego;

import java.util.List;
import java.util.Map;
import java.util.Random;

import batalla.Batallon;
import fabricas.Reclutador;
import hechizos.Hechizo;
import objetos.ObjetoMagico;
import personajes.Personaje;

public class Juego {

	private Batallon batallonMagos = new Batallon();
	private Batallon batallonMortifagos = new Batallon();
    private Random rand = new Random();
    private int ronda = 1;
    private int cantidadPersonajes;
    
    public Juego(int cantidadPersonajes) {
        this.cantidadPersonajes = cantidadPersonajes;
    }
    
	public void iniciarJuego() {
		
		mostrarTitulo();

        crearBatallones();
        
        mostrarSeparador();
        
        correrBuclePrincipal();

        mostrarFinPartida();
	}
	
	private void mostrarTitulo() {
		System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     BATALLA: MAGOS VS MORTÍFAGOS     ║");
        System.out.println("╚══════════════════════════════════════╝\n");
	}
	
	private void crearBatallones() {
		
		for (int i = 0; i < cantidadPersonajes; i++) {
            batallonMagos.agregarPersonaje(Reclutador.crearMago());
            batallonMortifagos.agregarPersonaje(Reclutador.crearMortifago());
        }

        System.out.println("Batallón de Magos:");
        mostrarBatallon(batallonMagos);

        System.out.println("\nBatallón de Mortífagos:");
        mostrarBatallon(batallonMortifagos);
	}
	
	private void mostrarSeparador() {
		System.out.println("\n══════════════════════════════════════\n");
	}
	
	private void correrBuclePrincipal() {
		while (batallonMagos.tienePersonajesSaludables() && batallonMortifagos.tienePersonajesSaludables()) {

            System.out.println("RONDA " + ronda);
            mostrarSeparador();

            if (rand.nextBoolean()) {
                System.out.println("Turno de los MAGOS:");
                batallonMagos.atacar(batallonMortifagos);

                if (batallonMortifagos.tienePersonajesSaludables()) {
                    System.out.println("\nTurno de los MORTÍFAGOS:");
                    batallonMortifagos.atacar(batallonMagos);
                }
            } else {
                System.out.println("Turno de los MORTÍFAGOS:");
                batallonMortifagos.atacar(batallonMagos);

                if (batallonMagos.tienePersonajesSaludables()) {
                    System.out.println("\nTurno de los MAGOS:");
                    batallonMagos.atacar(batallonMortifagos);
                }
            }

            System.out.println("\nEstado al final de la ronda:\n");
            System.out.println("Magos:");
            mostrarEstado(batallonMagos);
            System.out.println("Mortífagos:");
            mostrarEstado(batallonMortifagos);

            batallonMagos.limpiarEstadoRonda();
            batallonMortifagos.limpiarEstadoRonda();

            mostrarSeparador();
            ronda++;
        }
	}
	
	private void mostrarFinPartida() {
		System.out.println("══════════════════════════════════════");
        System.out.println("           FIN DE LA BATALLA");
        System.out.println("══════════════════════════════════════\n");

        if (batallonMagos.tienePersonajesSaludables()) {
            System.out.println("¡Los MAGOS han ganado la batalla!");
        } else {
            System.out.println("¡Los MORTÍFAGOS han ganado la batalla!");
        }

        System.out.println("\nHistorial de hechizos lanzados:");
        
        System.out.println("Magos:");
        mostrarHistorial(batallonMagos);
        System.out.println("Mortífagos:");
        mostrarHistorial(batallonMortifagos);
	}
	
	private static void mostrarBatallon(Batallon batallon) {
        for (Personaje p : batallon.getPersonajes()) {

            String objetos = "Ninguno";
            
            if (!p.getObjetosMagicos().isEmpty()) {
            	
                objetos = "";
                
                for (ObjetoMagico obj : p.getObjetosMagicos()) {
                    if (!objetos.isEmpty()) objetos += ", ";
                    objetos += obj.getNombre();
                }
            }

            System.out.println("  - " + p.getNombre()
                + " | Vida: " + p.getPuntosDeVida()
                + " | Magia: " + p.getNivelDeMagia()
                + " | Objetos: " + objetos
            );
        }
    }

    private static void mostrarEstado(Batallon batallon) {
        for (Personaje p : batallon.getPersonajes()) {
        	
            String estado = p.estaVivo() ? p.getPuntosDeVida() + " Puntos de Vida" : "Eliminado";
            System.out.println("    - " + p.getNombre() + ": " + estado);
        }
    }

    private static void mostrarHistorial(Batallon batallon) {
        Map<Personaje, List<Hechizo>> historial = batallon.getHistorialHechizos();
        
        for (Map.Entry<Personaje, List<Hechizo>> entrada : historial.entrySet()) {
        	
        	String hechizos = "";
        	
        	for (Hechizo h : entrada.getValue()) {
        	    if (!hechizos.isEmpty()) hechizos += ", ";
        	    hechizos += h.getClass().getSimpleName();
        	}
        	
        	if (hechizos.isEmpty()) hechizos = "ninguno";
        	
        	System.out.println("    - " + entrada.getKey().getNombre() + ": " + hechizos);
        }
    }
}
