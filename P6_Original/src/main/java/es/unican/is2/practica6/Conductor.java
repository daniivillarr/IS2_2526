package es.unican.is2.practica6;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	// WMC = 5 (1 base + 1 por el if + 3 por los operadores ||)
	// CCog = 4 (1 por el if + 3 por los operadores ||. Nivel de anidamiento 0)
	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		// WMC: +1 (if), +3 (||) | CCog: +1 (if), +3 (||)
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	// WMC = 1 (base). CCog = 0
	public String dni() {
		return dni;
	}

	// WMC = 1 (base). CCog = 0
	public String getDni() {
		return dni;
	}

	// WMC = 1 (base). CCog = 0
	public String getNombre() {
		return nombre;
	}

	// WMC = 1 (base). CCog = 0
	public String getApellido1() {
		return apellido1;
	}

	// WMC = 1 (base). CCog = 0
	public String apellido2() {
		return apellido2;
	}

	// WMC = 1 (base). CCog = 0
	public String getDire() {
		return dire;
	}

	// WMC = 6 (1 base + 1 for + 3 cases del switch + 1 if)
	// CCog = 6
	public double sueldo() {
		double sueldoTransportes = 0;
		
		// WMC: +1 (for) | CCog: +1 (for, aumenta anidamiento a 1)
		for (Transporte t : transportes) { 
			double sueldoExtraTransporte = 0.0;
			
			// WMC: 0 (el switch en sí no suma, suman sus cases) 
			// CCog: +2 (+1 por la estructura switch, +1 por estar anidado nivel 1)
			switch (t.categoria()) { 
				case Mercancias: // WMC: +1 (case)
					sueldoExtraTransporte = t.ton() * 2;
					break;
				case MercanciasPeligrosas: // WMC: +1 (case)
					sueldoExtraTransporte = t.ton() * 2 + 50;
					break;
				case Personas: // WMC: +1 (case)
					// WMC: +1 (if)
					// CCog: +3 (+1 por el if, +2 por estar anidado nivel 2: dentro de un for y un switch)
					if (t.getPersonas() < 10) 
						sueldoExtraTransporte = t.horas() * 0.5;
					else
						sueldoExtraTransporte = t.horas();
					break;
			}
			sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
		}
		return 700 + sueldoTransportes;
	}

	// WMC = 1 (base). CCog = 0
	public void anhadeTransporte(Transporte t) {
		transportes.add(t);
	}

}