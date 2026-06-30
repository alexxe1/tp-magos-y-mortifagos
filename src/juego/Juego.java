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
	private static final int CANTIDAD_PERSONAJES_POR_BATALLON = 3;
	private static final int RONDA_INICIAL = 1;
	
	private Batallon batallonMagos = new Batallon();
	private Batallon batallonMortifagos = new Batallon();
    private Random rand = new Random();
    private int ronda = RONDA_INICIAL;
    
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
		
		for (int i = 0; i < CANTIDAD_PERSONAJES_POR_BATALLON; i++) {
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
            String objetos;

            if (p.getObjetosMagicos().isEmpty()) {
                objetos = "Ninguno";
            } else {
                objetos = p.getObjetosMagicos().stream()
                    .map(ObjetoMagico::getNombre)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Ninguno");
            }

            System.out.println(
                "  - " + p.getNombre()
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
            System.out.print("    - " + entrada.getKey().getNombre() + ": ");
            if (entrada.getValue().isEmpty()) {
                System.out.println("ninguno");
            } else {
                System.out.println(entrada.getValue().stream()
                    .map(h -> h.getClass().getSimpleName())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("ninguno"));
            }
        }
    }
}
