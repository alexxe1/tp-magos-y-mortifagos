package fabricas;

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

    public static Mago crearMago() {
        switch (random.nextInt(3)) {
            case 0:
                return new Auror("Auror", 8, 120, hechizosDeMago());
            case 1:
                return new Profesor("Profesor", 10, 110, hechizosDeMago());
            default:
                return new Estudiante("Estudiante", 4, 85, hechizosDeMago());
        }
    }

    public static Mortifago crearMortifago() {
        switch (random.nextInt(2)) {
            case 0:
                return new Comandante("Comandante", 10, 120, hechizosDeMortifago());
            default:
                return new Seguidor("Seguidor", 5, 75, hechizosDeMortifago());
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
}
