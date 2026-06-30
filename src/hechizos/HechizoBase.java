package hechizos;

// Dos hechizos del mismo tipo se consideran iguales
public abstract class HechizoBase implements Hechizo {
	
	@Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
