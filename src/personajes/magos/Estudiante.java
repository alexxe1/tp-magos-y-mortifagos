package personajes.magos;

import java.util.List;

import hechizos.Hechizo;

public class Estudiante extends Mago {
    public Estudiante(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }
}
