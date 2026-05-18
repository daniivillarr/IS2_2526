package es.unican.is2.practica6;

import java.util.ArrayList;
import java.util.List;

public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	// WMC = 3 (1 base + 1 por el for + 1 por el if)
	// CCog = 3 
	public Conductor buscaConductor(String DNI) {		
		// WMC: +1 (for) | CCog: +1 (for, aumenta anidamiento a 1)
		for(Conductor c: cs) 
			// WMC: +1 (if) | CCog: +2 (+1 por el if, +1 por estar anidado dentro del for)
			if (c.dni().equals(DNI))
				return c;
		
		return null;
	}
	
	// WMC = 2 (1 base + 1 por el if)
	// CCog = 1
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		// WMC: +1 (if) | CCog: +1 (if, sin anidamiento previo)
		if (buscaConductor(dni) != null)
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	// WMC = 1 (base). CCog = 0
	public List<Conductor> conductores() {
		return cs;
	}
	
}