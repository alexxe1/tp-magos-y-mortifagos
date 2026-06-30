package personajes.magos;

import java.util.List;

import hechizos.Hechizo;

public class Auror extends Mago {
    private static final int BONIFICACION_DAÑO_AUROR = 10;
    
    public Auror(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }

    @Override
    public int calcularDaño(Hechizo hechizo) {
        return super.calcularDaño(hechizo) + BONIFICACION_DAÑO_AUROR;
    }
}
