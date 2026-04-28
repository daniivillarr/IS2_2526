package es.unican.is2.SegurosGUI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.jupiter.api.*;

import es.unican.is2.SegurosBusiness.GestionSeguros;
import es.unican.is2.SegurosDAOH2.ClientesDAO;
import es.unican.is2.SegurosDAOH2.SegurosDAO;
import es.unican.is2.SegurosCommon.DataAccessException;

public class ConsultaClienteIT {
	
	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() throws DataAccessException {
		// Montamos la lógica y los DAOs para que tire de la base de datos H2
		ClientesDAO clientesDAO = new ClientesDAO(); 
		SegurosDAO segurosDAO = new SegurosDAO();
		GestionSeguros g = new GestionSeguros(clientesDAO, segurosDAO);

		// Lanzamos la interfaz pasándole el negocio
		VistaAgente gui = GuiActionRunner.execute(() -> {
			VistaAgente v = new VistaAgente(g, g, g);
			v.setVisible(true);
			return v;
		});
		
		// Preparamos el robot de pruebas 
		window = new FrameFixture(gui);
		window.show();
		window.maximize();
	}
	
	@AfterEach
	public void tearDown() {
		// Cerramos todo al terminar cada test
		if (window != null) {
			window.cleanUp();
		}
	}
	
	@Test
	public void testConsultaClienteExistente() {
		// Test para Juan (DNI 11111111A) que tiene 3 seguros según la tabla
		window.textBox("txtDNICliente").deleteText().enterText("11111111A");
		
		// Click en el botón de buscar
		window.button("btnBuscar").click();		
		
		// Meto un sleep pequeño porque si no la base de datos a veces no llega a tiempo
		try { Thread.sleep(500); } catch (InterruptedException e) {}
		
		// Miramos que el nombre sea el de la tabla y que salgan sus 3 seguros
		window.textBox("txtNombreCliente").requireText("Juan");
		window.list().requireItemCount(3);
		
		// Chequeamos que el campo del total se haya rellenado con algo
		String total = window.textBox("txtTotalCliente").text();
		Assertions.assertNotNull(total);
		Assertions.assertFalse(total.trim().isEmpty(), "El total no puede estar vacío");
	}

	@Test
	public void testConsultaClienteNoExistente() {
		// Caso en el que el cliente no está en la base de datos
		window.textBox("txtDNICliente").deleteText().enterText("99999999Z");
		window.button("btnBuscar").click();
		
		// Esperamos a que refresque la pantalla
		try { Thread.sleep(500); } catch (InterruptedException e) {}
		
		// La vista debería decir que el DNI no vale y dejar la lista vacía
		window.textBox("txtNombreCliente").requireText("DNI No Valido");
		window.list().requireItemCount(0);
	}
}