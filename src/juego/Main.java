package juego;

public class Main {
	
    public static void main(String[] args) {

    	int cantidadDePersonajesPorBatallon = 3;
    	
    	Juego juego = new Juego(cantidadDePersonajesPorBatallon);
    	
    	juego.iniciarJuego();
    }
}