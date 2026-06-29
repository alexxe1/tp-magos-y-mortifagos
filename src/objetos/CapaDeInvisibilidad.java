package objetos;

public class CapaDeInvisibilidad extends ObjetoMagicoBase {
	private boolean puedeEsquivar = true;

	@Override
	public String getNombre() {
		return "Capa de invisibilidad";
	}

	@Override
	public boolean esquivarAtaque() {
		if (puedeEsquivar) {
			puedeEsquivar = false;
			
			return true;
		}

		return false;
	}
}
