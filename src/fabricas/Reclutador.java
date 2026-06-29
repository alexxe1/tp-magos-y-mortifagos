package fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import hechizos.Hechizo;
import hechizos.TipoHechizo;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Mago;
import personajes.magos.Profesor;
import personajes.mortifagos.Comandante;
import personajes.mortifagos.Mortifago;
import personajes.mortifagos.Seguidor;

public class Reclutador {
	private static Random random = new Random();
	
	private static List<String> nombresAuror = new ArrayList<>(Arrays.asList("Kingsley", "Tonks", "Moody", "Dawlish", "Proudfoot"));
	private static List<String> nombresProfesor = new ArrayList<>(Arrays.asList("Dumbledore", "McGonagall", "Lupin", "Snape", "Flitwick"));
	private static List<String> nombresEstudiante = new ArrayList<>(Arrays.asList("Harry", "Hermione", "Ron", "Neville", "Luna", "Ginny", "Draco"));
	private static List<String> nombresComandante = new ArrayList<>(Arrays.asList("Voldemort", "Bellatrix", "Lucius", "Greyback", "Dolohov"));
	private static List<String> nombresSeguidor = new ArrayList<>(Arrays.asList("Crabbe", "Goyle", "Pettigrew", "Avery", "Rookwood"));
	
    public static Mago crearMago() {
        switch (random.nextInt(3)) { 
            case 0:
            	return new Auror(elegirNombre(nombresAuror, "Auror"), 8, 120, hechizosDeMago());
            case 1:
            	return new Profesor(elegirNombre(nombresProfesor, "Profesor"), 10, 110, hechizosDeMago());
            default:
            	return new Estudiante(elegirNombre(nombresEstudiante, "Estudiante"), 4, 85, hechizosDeMago());
        }
    }

    public static Mortifago crearMortifago() {
        switch (random.nextInt(2)) {
            case 0:
            	return new Comandante(elegirNombre(nombresComandante, "Comandante"), 10, 120, hechizosDeMortifago());
            default:
            	return new Seguidor(elegirNombre(nombresSeguidor, "Seguidor"), 5, 75, hechizosDeMortifago());
        }
    }

    private static List<Hechizo> hechizosDeMago() {
        return List.of(
                Hechiceria.crearHechizo(TipoHechizo.EXPELLIARMUS),
                Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
                Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
    }
    
    private static List<Hechizo> hechizosDeMortifago() {
        return List.of(
                Hechiceria.crearHechizo(TipoHechizo.AVADA_KEDAVRA),
                Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
                Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
    }
    
    // Elige un nombre aleatorio de la lista según su clase o uno genérico si no hay más sin repetir
    private static String elegirNombre(List<String> nombres, String nombreGenerico) {
        if (nombres.isEmpty()) return nombreGenerico;
        
        int indice = random.nextInt(nombres.size());
        
        return nombres.remove(indice);
    }
}
