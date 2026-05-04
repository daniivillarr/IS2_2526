package es.unican.is2adt;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.adt.ConjuntoOrdenado;

public class ConjuntoOrdenadoTest {
    
    private ConjuntoOrdenado<Integer> conjunto;

    @Before
    public void setUp() {
        conjunto = new ConjuntoOrdenado<Integer>();
    }

    @Test
    public void testAdd() {
        // Añadimos elementos desordenados
        assertTrue(conjunto.add(20));
        assertTrue(conjunto.add(10));
        assertTrue(conjunto.add(15));
        
        // Comprobamos que no deja añadir repetidos (¡ESTO FALLARÁ POR LA TRAMPA!)
        assertFalse("Deberia devolver false al añadir duplicado", conjunto.add(10)); 
        
        // Comprobamos el tamaño (debe ser 3)
        assertEquals(3, conjunto.size());
        
        // Comprobamos que se han ordenado de menor a mayor (¡ESTO TAMBIÉN FALLARÁ!)
        assertEquals((Integer)10, conjunto.get(0));
        assertEquals((Integer)15, conjunto.get(1));
        assertEquals((Integer)20, conjunto.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        conjunto.add(null);
    }

    @Test
    public void testGetValidos() {
        conjunto.add(10);
        conjunto.add(20);
        assertEquals((Integer)10, conjunto.get(0));
        assertEquals((Integer)20, conjunto.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidoInferior() {
        conjunto.add(10);
        conjunto.get(-1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidoSuperior() {
        conjunto.add(10);
        conjunto.get(1); // Solo hay un elemento en la posicion 0
    }

    @Test
    public void testRemoveValido() {
        conjunto.add(10);
        conjunto.add(20);
        Integer borrado = conjunto.remove(0);
        
        assertEquals((Integer)10, borrado);
        assertEquals(1, conjunto.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalido() {
        conjunto.add(10);
        conjunto.remove(5);
    }
    
    @Test
    public void testClear() {
        conjunto.add(10);
        conjunto.add(20);
        
        conjunto.clear();
        
        // Comprobamos que el tamaño ahora es 0
        assertEquals(0, conjunto.size());
    }
}
