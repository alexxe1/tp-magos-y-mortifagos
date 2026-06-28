package personajes.magos;

import java.util.List;

import hechizos.Hechizo;
import personajes.Personaje;

public abstract class Mago extends Personaje {
    protected Mago(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }

    @Override
    public int calcularDaño(Hechizo hechizo) {
        return hechizo.getPotenciaBase() + getNivelDeMagia();
    }

    @Override
    public int calcularCuracion(Hechizo hechizo) {
        return hechizo.getPotenciaBase() + getNivelDeMagia();
    }
}