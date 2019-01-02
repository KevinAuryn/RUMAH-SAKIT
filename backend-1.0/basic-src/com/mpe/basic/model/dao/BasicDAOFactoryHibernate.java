package com.mpe.basic.model.dao;

import java.io.Serializable;

import com.backend.dao.UsersDAO;
import com.backend.dao.UsersDAOHibernate;
import com.mpe.common.dao.GenericHibernateDAO;


/**
 * @author Agung Hadiwaluyo
 *
 */
public class BasicDAOFactoryHibernate extends BasicDAOFactory implements Serializable {
	
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
	public UsersDAO getUsersDAO() {
		return (UsersDAO) instantiateDAO(UsersDAOHibernate.class);
	}
	
	/*@Override
	public UserDAO getUserDAO() {
		return (UserDAO) instantiateDAO(UserDAOHibernate.class);
	}
	
	@Override
	public LookupDAO getLookupDAO() {
		return (LookupDAO) instantiateDAO(LookupDAOHibernate.class);
	}*/

	@Override
	public PermissionDAO getPermissionDAO() {
		return (PermissionDAO) instantiateDAO(PermissionDAOHibernate.class);
	}

	/*@Override
	public RoleDAO getRoleDAO() {
		return (RoleDAO) instantiateDAO(RoleDAOHibernate.class);
	}

	@Override
	public StatusDAO getStatusDAO() {
		return (StatusDAO) instantiateDAO(StatusDAOHibernate.class);
	}

	@Override
	public OrganizationDAO getOrganizationDAO() {
		return (OrganizationDAO) instantiateDAO(OrganizationDAOHibernate.class);
	}

	@Override
	public UserActivityDAO getUserActivityDAO() {
		return (UserActivityDAO) instantiateDAO(UserActivityDAOHibernate.class);
	}

	@Override
	public ApplicationSetupDAO getApplicationSetupDAO() {
		return (ApplicationSetupDAO) instantiateDAO(ApplicationSetupDAOHibernate.class);
	}

	@Override
	public BranchDAO getBranchDAO() {
		return (BranchDAO) instantiateDAO(BranchDAOHibernate.class);
	}

	@Override
	public BranchClassDAO getBranchClassDAO() {
		return (BranchClassDAO) instantiateDAO(BranchClassDAOHibernate.class);
	}

	@Override
	public BranchTypeDAO getBranchTypeDAO() {
		return (BranchTypeDAO) instantiateDAO(BranchTypeDAOHibernate.class);
	}

	@Override
	public UserPasswordHistoryDAO getUserPasswordHistoryDAO() {
		return (UserPasswordHistoryDAO) instantiateDAO(UserPasswordHistoryDAOHibernate.class);
	}

	@Override
	public LocationDAO getLocationDAO() {
		return (LocationDAO) instantiateDAO(LocationDAOHibernate.class);
	}*/

	/* (non-Javadoc)
	 * @see com.mpe.basic.model.dao.BasicDAOFactory#getWorkOffDayDAO()
	 */
	/*@Override
	public WorkOffDayDAO getWorkOffDayDAO() {
		return (WorkOffDayDAO) instantiateDAO(WorkOffDayDAOHibernate.class);
	}

	@Override
	public RunningNumberDAO getRunningNumberDAO() {
		return (RunningNumberDAO) instantiateDAO(RunningNumberDAOHibernate.class);
	}

	@Override
	public UserDelegationDAO getUserDelegationDAO() {
		return (UserDelegationDAO) instantiateDAO(UserDelegationDAOHibernate.class);
	}

	@Override
	public RegisteredDeviceDAO getRegisteredDeviceDAO() {
		return (RegisteredDeviceDAO) instantiateDAO(RegisteredDeviceDAOHibernate.class);
	}

	@Override
	public UserSecurityQuestionDAO getUserSecurityQuestionDAO() {
		return (UserSecurityQuestionDAO) instantiateDAO(UserSecurityQuestionDAOHibernate.class);
	}

	@Override
	public UserSecugenDAO getUserSecugenDAO() {
		return (UserSecugenDAO) instantiateDAO(UserSecugenDAOHibernate.class);
	}
	
	@Override
	public UniqueNumberDAO getUniqueNumberDAO() {
		return (UniqueNumberDAO) instantiateDAO(UniqueNumberDAOHibernate.class);
	}*/
}
