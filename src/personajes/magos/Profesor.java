package personajes.magos;

import java.util.List;

import hechizos.Hechizo;

public class Profesor extends Mago {
    private static final int BONIFICACION_DANIO_PROFESOR = 5;
    private static final int BONIFICACION_CURACION_PROFESOR = 15;
    
    public Profesor(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }
    
    @Override
    public int calcularDaño(Hechizo hechizo) {
    	return super.calcularDaño(hechizo) + BONIFICACION_DANIO_PROFESOR;
    }

    @Override
    public int calcularCuracion(Hechizo hechizo) {
        return super.calcularCuracion(hechizo) + BONIFICACION_CURACION_PROFESOR;
    }
}
