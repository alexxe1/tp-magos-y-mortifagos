package personajes.magos;

import java.util.List;

import hechizos.Hechizo;
import personajes.Personaje;

public abstract class Mago extends Personaje {
    private static final int BONIFICACION_DANIO_MAGO = 3;
    
    protected Mago(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }

    @Override
    public int calcularDaño(Hechizo hechizo) {
        return aplicarObjetosAlDaño(hechizo, hechizo.getPotenciaBase() + getNivelDeMagia() + BONIFICACION_DANIO_MAGO);
    }

    @Override
    public int calcularCuracion(Hechizo hechizo) {
        return aplicarObjetosALaCuracion(hechizo.getPotenciaBase() + getNivelDeMagia());
    }
}
