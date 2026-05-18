package es.unican.is2.practica6;

public abstract class Transporte {
	
	protected double horas; // Cambiado a protected para que lo vean las hijas
	
	public Transporte(double horas) {
		if (horas <= 0) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}
	
	public double horas() {
		return horas;
	}

	// Método abstracto: obligamos a que cada subclase calcule su extra
	public abstract double costeExtra(); 
}