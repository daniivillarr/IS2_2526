package es.unican.is2.adt;
import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;
/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public boolean add(E elemento) {
		int indice = 0;
		if (elemento==null)
			throw new NullPointerException();
		
		// ARREGLO 1: Comprobar si ya existe para no admitir duplicados
		if (lista.contains(elemento)) {
			return false;
		}
		
		if (lista.size() != 0) {
			// ARREGLO 2: Cambiar '< 0' por '> 0' para ordenar de menor a mayor
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
				indice++;
			}
		}
		lista.add(indice, elemento);
		return true;
	}

	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		lista.clear();
	}

}