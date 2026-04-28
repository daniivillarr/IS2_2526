package es.unican.is2.SegurosCommon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class SeguroTest {


    // 1.Estado de la Fecha

    @Test
    public void testPrecio_PE_FechaNula() {
        Seguro s = new Seguro();
        s.setFechaInicio(null);
        assertEquals(0.0, s.precio(), 0.001, "CP1: Fecha nula debe devolver 0");
    }

    @Test
    public void testPrecio_PE_FechaFutura() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().plusDays(1));
        assertEquals(0.0, s.precio(), 0.001, "CP2: Fecha en el futuro debe devolver 0");
    }


    // 2.  Tipos de Cobertura

    @Test
    public void testPrecio_PE_CoberturaNula() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2)); // En vigor
        s.setPotencia(50); // Sin recargo
        s.setCobertura(null);
        assertEquals(0.0, s.precio(), 0.001, "CP3: Cobertura nula da base 0");
    }

    @Test
    public void testPrecio_PE_CoberturaTerceros() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setPotencia(50);
        s.setCobertura(Cobertura.TERCEROS);
        assertEquals(400.0, s.precio(), 0.001, "CP4: Base de Terceros es 400");
    }

    @Test
    public void testPrecio_PE_CoberturaTercerosLunas() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setPotencia(50);
        s.setCobertura(Cobertura.TERCEROS_LUNAS);
        assertEquals(600.0, s.precio(), 0.001, "CP5: Base de Terceros Lunas es 600");
    }

    @Test
    public void testPrecio_PE_CoberturaTodoRiesgo() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setPotencia(50);
        s.setCobertura(Cobertura.TODO_RIESGO);
        assertEquals(1000.0, s.precio(), 0.001, "CP6: Base de Todo Riesgo es 1000");
    }

    // 3. ANÁLISIS DE VALORES LÍMITE (AVL): Recargos por Potencia

    @Test
    public void testPrecio_AVL_Potencia89() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setCobertura(Cobertura.TERCEROS);
        
        s.setPotencia(89);
        assertEquals(400.0, s.precio(), 0.001, "CP7: Potencia < 90 no tiene recargo");
    }

    @Test
    public void testPrecio_AVL_Potencia90() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setCobertura(Cobertura.TERCEROS);
        
        s.setPotencia(90);
        assertEquals(420.0, s.precio(), 0.001, "CP8: Potencia >= 90 suma 5% (+20)");
    }

    @Test
    public void testPrecio_AVL_Potencia110() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setCobertura(Cobertura.TERCEROS);
        
        s.setPotencia(110);
        assertEquals(420.0, s.precio(), 0.001, "CP9: Potencia <= 110 suma 5% (+20)");
    }

    @Test
    public void testPrecio_AVL_Potencia111() {
        Seguro s = new Seguro();
        s.setFechaInicio(LocalDate.now().minusYears(2));
        s.setCobertura(Cobertura.TERCEROS);
        
        s.setPotencia(111);
        assertEquals(480.0, s.precio(), 0.001, "CP10: Potencia > 110 suma 20% (+80)");
    }

 
    // 4.  Descuentos por Antigüedad
    

    @Test
    public void testPrecio_PE_DescuentoNuevo() {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TERCEROS); // Base 400
        s.setPotencia(50); // Sin recargo
        
        // Contrato de menos de 1 año (año 0) -> 20% descuento (400 - 80)
        s.setFechaInicio(LocalDate.now().minusMonths(6));
        assertEquals(320.0, s.precio(), 0.001, "CP11: Contrato de < 1 año descuenta un 20%");
    }
}