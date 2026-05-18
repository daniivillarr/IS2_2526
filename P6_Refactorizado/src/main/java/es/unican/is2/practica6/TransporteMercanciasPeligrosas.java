package es.unican.is2.practica6;

public class TransporteMercanciasPeligrosas extends TransporteMercancias {

	public TransporteMercanciasPeligrosas(double horas, int ton) {
		super(horas, ton);
	}

	@Override
	public double costeExtra() {
		return super.costeExtra() + 50; // Reutiliza el cálculo y suma 50
	}
}