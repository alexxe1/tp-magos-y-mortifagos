package objetos;

public class AmuletoDeRecuperacion extends ObjetoMagicoBase {
	@Override
	public String getNombre() {
		return "Amuleto de recuperacion";
	}

	@Override
	public int modificarCuracion(int curacion) {
		return curacion + 10;
	}
}
