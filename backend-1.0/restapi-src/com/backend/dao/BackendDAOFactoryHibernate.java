package com.backend.dao;

import java.io.Serializable;

import com.mpe.common.dao.GenericHibernateDAO;

public class BackendDAOFactoryHibernate extends BackendDAOFactory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private GenericHibernateDAO instantiateDAO(Class daoClass) {
		try {
			GenericHibernateDAO dao = (GenericHibernateDAO) daoClass.newInstance();
			return dao;
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
		}
	}

	@Override
	public AppConfigDAO getAppConfigDAO() {
		return (AppConfigDAO) instantiateDAO(AppConfigDAOHibernate.class);
	}
	
	public UsersDAO getUsersDAO() {
		return (UsersDAO) instantiateDAO(UsersDAOHibernate.class);
	}
	

}

