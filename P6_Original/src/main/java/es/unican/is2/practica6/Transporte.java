package es.unican.is2.practica6;

/* Clase que representa un transporte realizado por un conductor */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	// WMC = 5 (1 base + 1 por el primer if + 2 por los || + 1 por el segundo if)
	// CCog = 5 (1 por el primer if + 2 por los || + 1 por el segundo if + 1 por el else)
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {
		// WMC: +1 (if), +2 (||) | CCog: +1 (if), +2 (||)
		if (horas <= 0 || valor <= 0 || cat == null) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		
		// WMC: +1 (if) | CCog: +1 (if)
		if (cat.equals(CategoriaTransporte.Personas)) {
			this.personas = valor;
		// WMC: 0 (el else no es una nueva decisión) | CCog: +1 (else)
		} else  {
			this.ton = valor;
		}
	}
	
	// WMC = 1 (base). CCog = 0
	public double horas() {
		return horas;
	}

	// WMC = 1 (base). CCog = 0
	public CategoriaTransporte categoria() {
		return cat;
	}

	// WMC = 1 (base). CCog = 0
	public int ton() {
		return ton;
	}

	// WMC = 1 (base). CCog = 0
	public int getPersonas() {
		return personas;
	}
	
}