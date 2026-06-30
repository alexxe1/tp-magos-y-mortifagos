package personajes.magos;

import java.util.List;

import hechizos.Hechizo;

public class Profesor extends Mago {
    private static final int BONIFICACION_DANIO_PROFESOR = 5;
    
    public Profesor(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
        super(nombre, nivelDeMagia, puntosDeVida, hechizosParaLanzar);
    }
    
    @Override
    public int calcularDaño(Hechizo hechizo) {
    	return super.calcularDaño(hechizo) + BONIFICACION_DANIO_PROFESOR;
    }

}
