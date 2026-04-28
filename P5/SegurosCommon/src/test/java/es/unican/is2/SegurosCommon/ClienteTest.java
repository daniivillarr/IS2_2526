package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ClienteTest {

    // Método: totalSeguros()

    @Test
    public void testTotalSeguros_SinSeguros() {
      
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setDni("11111111A");

        assertEquals(0.0, cliente.totalSeguros(), 0.001, "Sin seguros debe pagar 0.0");
    }

    @Test
    public void testTotalSeguros_UnSeguro() {
    
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        cliente.setDni("22222222A");

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS); 
        s1.setFechaInicio(LocalDate.now().minusYears(2)); 
        s1.setPotencia(50); 
        
        cliente.getSeguros().add(s1);
        
        assertEquals(400.0, cliente.totalSeguros(), 0.001, "Debe coincidir con el precio del único seguro (400)");
    }

    @Test
    public void testTotalSeguros_VariosSeguros() {
  
        Cliente cliente = new Cliente();
        cliente.setNombre("Luis");
        cliente.setDni("33333333A");

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS); // Base: 400
        s1.setFechaInicio(LocalDate.now().minusYears(2)); 
        s1.setPotencia(50); 
        
        Seguro s2 = new Seguro();
        s2.setCobertura(Cobertura.TODO_RIESGO); // Base: 1000
        s2.setFechaInicio(LocalDate.now().minusYears(2)); 
        s2.setPotencia(50); 
        
        cliente.getSeguros().add(s1);
        cliente.getSeguros().add(s2);
        
        assertEquals(1400.0, cliente.totalSeguros(), 0.001, "Debe sumar el total de todos los seguros (1400)");
    }
    
    @Test
    public void testTotalSeguros_SeguroNoEnVigor() {

        Cliente cliente = new Cliente();
        
        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS);
        s1.setFechaInicio(LocalDate.now().plusDays(10)); 
        s1.setPotencia(50);
        
        cliente.getSeguros().add(s1);
        
        assertEquals(0.0, cliente.totalSeguros(), 0.001, "Los seguros no en vigor no suman al total");
    }
}