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

<<<<<<< Updated upstream
    private List<Hechizo> hechizosDeMago() {
        return List.of(
                Hechiceria.crearHechizo(TipoHechizo.EXPELLIARMUS),
                Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
                Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
    }
}
=======
	private static List<String> nombresAuror = new ArrayList<>(
			Arrays.asList("Kingsley", "Tonks", "Moody", "Dawlish", "Proudfoot"));
	private static List<String> nombresProfesor = new ArrayList<>(
			Arrays.asList("Dumbledore", "McGonagall", "Lupin", "Snape", "Flitwick"));
	private static List<String> nombresEstudiante = new ArrayList<>(
			Arrays.asList("Harry", "Hermione", "Ron", "Neville", "Luna", "Ginny", "Draco"));
	private static List<String> nombresComandante = new ArrayList<>(
			Arrays.asList("Voldemort", "Bellatrix", "Lucius", "Greyback", "Dolohov"));
	private static List<String> nombresSeguidor = new ArrayList<>(
			Arrays.asList("Crabbe", "Goyle", "Pettigrew", "Avery", "Rookwood"));

	public static Mago crearMago() {

		Mago mago;
		TipoMago tipo = TipoMago.values()[random.nextInt(TipoMago.values().length)];

		switch (tipo) {

		case AUROR:
			mago = new Auror(elegirNombre(nombresAuror, "Auror"), NIVEL_MAGIA_AUROR, PUNTOS_VIDA_AUROR,
					hechizosDeMago());
			break;

		case PROFESOR:
			mago = new Profesor(elegirNombre(nombresProfesor, "Profesor"), NIVEL_MAGIA_PROFESOR, PUNTOS_VIDA_PROFESOR,
					hechizosDeMago());
			break;

		case ESTUDIANTE:
			mago = new Estudiante(elegirNombre(nombresEstudiante, "Estudiante"), NIVEL_MAGIA_ESTUDIANTE,
					PUNTOS_VIDA_ESTUDIANTE, hechizosDeMago());
			break;

		default:
			throw new IllegalStateException("Ese mago no existe!");
		}

		List<ObjetoMagico> objetos = TiendaDeObjetos.generarObjetosAleatorios();

		for (ObjetoMagico obj : objetos) {
			mago.equiparObjeto(obj);
		}

		return mago;
	}

	public static Mortifago crearMortifago() {

		Mortifago mortifago;
		TipoMortifago tipo = TipoMortifago.values()[random.nextInt(TipoMortifago.values().length)];

		switch (tipo) {

		case COMANDANTE:
			mortifago = new Comandante(elegirNombre(nombresComandante, "Comandante"), NIVEL_MAGIA_COMANDANTE,
					PUNTOS_VIDA_COMANDANTE, hechizosDeMortifago());
			break;

		case SEGUIDOR:
			mortifago = new Seguidor(elegirNombre(nombresSeguidor, "Seguidor"), NIVEL_MAGIA_SEGUIDOR, PUNTOS_VIDA_SEGUIDOR,
					hechizosDeMortifago());
			break;

		default:
			throw new IllegalStateException("Ese mortifago no existe!");
		}
		
		List<ObjetoMagico> objetos = TiendaDeObjetos.generarObjetosAleatorios();

		for (ObjetoMagico obj : objetos) {
		    mortifago.equiparObjeto(obj);
		}
		
		return mortifago;
	}

	private static List<Hechizo> hechizosDeMago() {
		return List.of(Hechiceria.crearHechizo(TipoHechizo.EXPELLIARMUS), Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
				Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
	}

	private static List<Hechizo> hechizosDeMortifago() {
		return List.of(Hechiceria.crearHechizo(TipoHechizo.AVADA_KEDAVRA), Hechiceria.crearHechizo(TipoHechizo.PROTEGO),
				Hechiceria.crearHechizo(TipoHechizo.EXPECTO_PATRONUM));
	}

	// Elige un nombre aleatorio de la lista según su clase o uno genérico si no hay
	// más sin repetir
	private static String elegirNombre(List<String> nombres, String nombreGenerico) {
		if (nombres.isEmpty())
			return nombreGenerico;

		int indice = random.nextInt(nombres.size());

		return nombres.remove(indice);
	}
}
>>>>>>> Stashed changes
