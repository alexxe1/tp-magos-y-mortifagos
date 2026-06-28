package personajes.magos;

import java.util.List;

import hechizos.Hechizo;

public class Profesor extends Mago {
    public Profesor(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }

    @Override
    public int calcularCuracion(Hechizo hechizo) {
        return super.calcularCuracion(hechizo) + 15;
    }
}
