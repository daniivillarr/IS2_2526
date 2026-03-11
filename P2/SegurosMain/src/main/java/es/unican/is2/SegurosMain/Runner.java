package es.unican.is2.SegurosMain;

import es.unican.is2.SegurosBusiness.GestionSeguros;
import es.unican.is2.SegurosCommon.ISegurosDAO;
import es.unican.is2.SegurosDAOH2.ClientesDAO;
import es.unican.is2.SegurosDAOH2.SegurosDAO;
import es.unican.is2.SegurosGUI.VistaAgente;

public class Runner {

	public static void main(String[] args) {
		ClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);
	}

}
