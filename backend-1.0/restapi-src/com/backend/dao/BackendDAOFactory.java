package com.backend.dao;


public abstract class BackendDAOFactory {
	/**
	* Factory method for instantiation of concrete factories.
	*/
	@SuppressWarnings("rawtypes")
	public static BackendDAOFactory instance(Class factory) {
		try {
			return (BackendDAOFactory)factory.newInstance();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Couldn't create DAOFactory: " + factory);
		}
	}
	
	// Add your DAO interfaces here
	public abstract AppConfigDAO getAppConfigDAO();
	public abstract UsersDAO getUsersDAO();
	;
	
	
}

