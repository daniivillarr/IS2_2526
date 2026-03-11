package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.*;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {
	
	private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;
	
	public GestionSeguros(IClientesDAO clientesDao, ISegurosDAO segurosDao) {
		this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
	}
	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		// Auto-generated method stub
		return this.clientesDAO.cliente(dni);
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return this.segurosDAO.seguroPorMatricula(matricula);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente c = this.clientesDAO.cliente(dni);
        
        if (c == null) {
            throw new OperacionNoValida("El cliente con DNI " + dni + " no existe.");
        }
       
        if (this.segurosDAO.seguroPorMatricula(s.getMatricula()) != null) {
            throw new OperacionNoValida("La matrícula " + s.getMatricula() + " ya tiene un seguro.");
        }

     
        Seguro seguroCreado = this.segurosDAO.creaSeguro(s);
        if (seguroCreado == null) {
            throw new OperacionNoValida("Error: No se pudo crear el seguro.");
        }
        
        c.getSeguros().add(seguroCreado);
        this.clientesDAO.actualizaCliente(c);
        
        return seguroCreado;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		Cliente c = this.clientesDAO.cliente(dni);
        Seguro s = this.segurosDAO.seguroPorMatricula(matricula);
      
        if (c == null || s == null) {
            throw new OperacionNoValida("Cliente o seguro no encontrado.");
        }
        
        boolean pertenece = false;
        for (Seguro seguroCliente : c.getSeguros()) {
            if (seguroCliente.getMatricula().equals(matricula)) {
                pertenece = true;
                break; 
            }
        }
        
        if (!pertenece) {
            throw new OperacionNoValida("El seguro no pertenece al cliente indicado.");
        }

        this.segurosDAO.eliminaSeguro(s.getId());

        c.getSeguros().removeIf(seg -> seg.getMatricula().equals(matricula));
        this.clientesDAO.actualizaCliente(c);
        
        return s;
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro s = this.segurosDAO.seguroPorMatricula(matricula);
        if (s != null) {
            s.setConductorAdicional(conductor);
            this.segurosDAO.actualizaSeguro(s);
        }
        
        return s;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		//Auto-generated method stub
		return this.clientesDAO.creaCliente(c);
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		//Auto-generated method stub
		Cliente c = this.clientesDAO.cliente(dni);
        
        if (c == null) {
            return null; 
        }
       
        if (!c.getSeguros().isEmpty()) {
            throw new OperacionNoValida("No se puede dar de baja: el cliente tiene seguros contratados.");
        }
        
        return this.clientesDAO.eliminaCliente(dni);
	}

}
