package objetos;

public class AmuletoDeRecuperacion extends ObjetoMagicoBase {
	
	private static final int CURACION_EXTRA = 10;
	
	@Override
	public String getNombre() {
		return "Amuleto de recuperacion";
	}

	@Override
	public int modificarCuracion(int curacion) {
		return curacion + CURACION_EXTRA;
	}
}
