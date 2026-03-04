package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.*;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
