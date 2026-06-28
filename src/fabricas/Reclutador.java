package fabricas;

import java.util.List;
import java.util.Random;

import hechizos.Hechizo;
import hechizos.TipoHechizo;
import personajes.magos.Auror;
import personajes.magos.Estudiante;
import personajes.magos.Mago;
import personajes.magos.Profesor;
import personajes.mortifagos.Mortifago;

public class Reclutador {
    private Random random = new Random();

    public Mago crearMago() {
        switch (random.nextInt(3)) {
            case 0:
                return new Auror("Auror", 8, 120, hechizosDeMago());
            case 1:
                return new Profesor("Profesor", 10, 110, hechizosDeMago());
            default:
                return new Estudiante("Estudiante", 4, 85, hechizosDeMago());
        }
    }

    public Mortifago crearMortifago() {
        return new Mortifago() {
        };
    }

    private List<Hechizo> hechizosDeMago() {
        return List.of(
                Hechiceria.crearHechizo(TipoHechizo.EXPELLIARMUS),
                Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
                Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
    }
}
