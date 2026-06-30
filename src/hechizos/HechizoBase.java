package hechizos;

// El propósito de esta clase es evitar tener implementar "equals" y "hashCode" en cada hechizo nuevo
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
