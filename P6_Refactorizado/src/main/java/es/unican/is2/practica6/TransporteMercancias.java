package es.unican.is2.practica6;

public class TransporteMercancias extends Transporte {
	private int ton;

	public TransporteMercancias(double horas, int ton) {
		super(horas);
		if (ton <= 0) throw new IllegalArgumentException();
		this.ton = ton;
	}

	public int ton() { return ton; }

	@Override
	public double costeExtra() {
		return this.ton * 2;
	}
}