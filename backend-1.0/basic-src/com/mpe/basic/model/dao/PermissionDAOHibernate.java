package com.mpe.basic.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Permission;
import com.mpe.common.dao.GenericHibernateDAO;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class PermissionDAOHibernate extends GenericHibernateDAO<Permission, Long> implements PermissionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> findByNullParent() {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		List<Permission> list = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Permission.class);
			criteria.add(Restrictions.isNull("parent"));
			list = criteria.list();
			transaction.commit();
		} catch(Exception exception){
			if (transaction!=null)transaction.rollback();
		} finally {
			if (session!=null) session.close();
		}
		return list;
	}

	
	
}
