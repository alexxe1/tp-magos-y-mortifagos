package personajes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hechizos.Hechizo;
import objetos.ObjetoMagico;

public abstract class Personaje {
	private static final int PUNTOS_DE_VIDA_MINIMOS = 0;
	
	private String nombre;
	private int nivelDeMagia;
	private int puntosDeVida;
	private int puntosDeVidaMaximos;
	private List<Hechizo> hechizosParaLanzar;
	private List<ObjetoMagico> objetosMagicos;
	private boolean estaProtegido;
	
	public Personaje(String nombre, int nivelDeMagia, int puntosDeVida, List<Hechizo> hechizosParaLanzar) {
		this.nombre = nombre;
		this.nivelDeMagia = nivelDeMagia;
		this.puntosDeVida = puntosDeVida;
		this.hechizosParaLanzar = new ArrayList<>(hechizosParaLanzar);
		this.objetosMagicos = new ArrayList<>();
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

	public void herir(int puntosDeDaño) {
		
		// No puede ser atacado si está protegido
		if (estaProtegido || esquivarAtaqueConObjeto()) {
			return;
		}
		
		puntosDeVida -= puntosDeDaño;
		
		if (puntosDeVida < PUNTOS_DE_VIDA_MINIMOS) {
			puntosDeVida = PUNTOS_DE_VIDA_MINIMOS;
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
	
	public Hechizo elegirHechizo() {
		return elegirHechizoAleatorio();
	}

	public Hechizo elegirHechizoAleatorio() {

		// Actualmente lanza un hechizo aleatorio pero se puede sofisticar
		Random random = new Random();
		int indiceRandom = random.nextInt(hechizosParaLanzar.size());
		
		return hechizosParaLanzar.get(indiceRandom);
	}
	
	public List<Hechizo> getHechizosParaLanzar() {
	    return hechizosParaLanzar;
	}

	public void equiparObjeto(ObjetoMagico objetoMagico) {
		objetosMagicos.add(objetoMagico);
	}

	public void quitarObjeto(ObjetoMagico objetoMagico) {
		objetosMagicos.remove(objetoMagico);
	}

	public List<ObjetoMagico> getObjetosMagicos() {
		return objetosMagicos;
	}

	protected int aplicarObjetosAlDaño(Hechizo hechizo, int daño) {
		int dañoFinal = daño;

		for (ObjetoMagico objetoMagico : objetosMagicos) {
			dañoFinal = objetoMagico.modificarDaño(hechizo, dañoFinal);
		}

		return dañoFinal;
	}

	protected int aplicarObjetosALaCuracion(int curacion) {
		int curacionFinal = curacion;

		for (ObjetoMagico objetoMagico : objetosMagicos) {
			curacionFinal = objetoMagico.modificarCuracion(curacionFinal);
		}

		return curacionFinal;
	}
	
	public boolean estaVivo() {
		return puntosDeVida > PUNTOS_DE_VIDA_MINIMOS;
	}
	
	// Esta función sirve para cuando un batallón ya usó todos los hechizos y los personajes no tienen más
	// hechizos disponibles sin repetir
	public void atacarSinHechizo(Personaje objetivo) {
	    objetivo.herir(nivelDeMagia);
	}
	
	// Esta función se llama cada vez que termina una ronda y debe reiniciar valores temporales
	public void limpiarEstadoRonda() {
		estaProtegido = false;
	}

	private boolean esquivarAtaqueConObjeto() {
		for (ObjetoMagico objetoMagico : objetosMagicos) {
			if (objetoMagico.esquivarAtaque()) {
				return true;
			}
		}

		return false;
	}
	
	public abstract int calcularDaño(Hechizo hechizo);
	public abstract int calcularCuracion(Hechizo hechizo);
}
