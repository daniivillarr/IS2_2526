package es.unican.is2.practica6;

public class TransportePersonas extends Transporte {
	private int personas;

	public TransportePersonas(double horas, int personas) {
		super(horas);
		if (personas <= 0) throw new IllegalArgumentException();
		this.personas = personas;
	}

	public int getPersonas() { return personas; }

	@Override
	public double costeExtra() {
		if (this.personas < 10) return this.horas * 0.5;
		return this.horas;
	}
}