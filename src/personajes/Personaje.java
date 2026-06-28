package personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hechizos.Hechizo;

public abstract class Personaje {
	private String nombre;
	private int nivelDeMagia;
	private int puntosDeVida;
	private int puntosDeVidaMaximos;
	private List<Hechizo> hechizosParaLanzar;
	private boolean estaProtegido;
	
	public Personaje(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		this.nombre = nombre;
		this.nivelDeMagia = nivelDeMagia;
		this.puntosDeVida = puntosDeVida;
		this.hechizosParaLanzar = new ArrayList<>(hechizosParaLanzar);
		this.puntosDeVidaMaximos = puntosDeVida;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPuntosDeVida() {
		return puntosDeVida;
	}
	
	public int getNivelDeMagia() {
		return nivelDeMagia;
	}

	public int getNivelMagia() {
		return nivelDeMagia;
	}
	
	public void herir(int puntosDeDaño) {
		
		// No puede ser atacado si está protegido
		if (estaProtegido) {
			return;
		}
		
		puntosDeVida -= puntosDeDaño;
		
		if (puntosDeVida < 0) {
			puntosDeVida = 0;
		}
	}
	
	public void curar(int puntosDeCuracion) {
		
		puntosDeVida += puntosDeCuracion;
		
		if (puntosDeVida > puntosDeVidaMaximos) {
			puntosDeVida = puntosDeVidaMaximos;
		}
	}
	
	public void setEstaProtegido(boolean valor) {
		estaProtegido = valor;
	}
	
	public Hechizo elegirHechizo() throws Exception {
		return elegirHechizoAleatorio();
	}

	public Hechizo elegirHechizoAleatorio() {

		// Actualmente lanza un hechizo aleatorio pero se puede sofisticar
		Random random = new Random();
		int indiceRandom = random.nextInt(hechizosParaLanzar.size());
		
		return hechizosParaLanzar.get(indiceRandom);
	}
	
	// Esta función se llama cada vez que termina una ronda y debe reiniciar valores temporales
	public void limpiarEstadoRonda() {
		estaProtegido = false;
	}
	
	public abstract int calcularDaño(Hechizo hechizo);
	public abstract int calcularCuracion(Hechizo hechizo);
}
