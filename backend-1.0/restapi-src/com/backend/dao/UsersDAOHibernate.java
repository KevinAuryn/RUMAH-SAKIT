package com.backend.dao;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.model.Users;
import com.backend.other.Message;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.util.CommonUtil;

public class UsersDAOHibernate extends GenericHibernateDAO<Users, Long>
		implements UsersDAO {
	
	Logger logger = LoggerFactory.getLogger(UsersDAOHibernate.class.getSimpleName());
	Users user = new Users();
	
	BackendDAOFactory backendDAOFactory = BackendDAOFactory.instance(BackendDAOFactoryHibernate.class);
	
	@Override
	public HashMap<String, Object> getUsers(Session session, HashMap<String, Object> request) throws Exception {
		Users us = null;
		HashMap<String, Object> callback = new HashMap<String, Object>();
		try {
			if(request != null) {
				String userName = CommonUtil.getRequestVal(request, "username");
				String password = CommonUtil.getRequestVal(request, "password");
				if (userName != null && userName.length() > 0 && password != null && password.length() > 0) {
					us = backendDAOFactory.getUsersDAO().findByCriteria(session, Restrictions.eq("userName", userName).ignoreCase(),Restrictions.eq("userPass", password));
					if (us != null) {
						callback.put("responseStatus",Message.SUCCESS);
						callback.put("responseMessage",Message.SUCCESS);
					} else {
						callback.put("responseStatus",Message.WRONG);
						callback.put("responseMessage",Message.WRONG);
					}
				} else {
					callback.put("responseStatus",Message.KOSONG);
					callback.put("responseMessage",Message.KOSONG);
				}
			}else {
				callback.put("responseStatus",Message.REQNULL);
				callback.put("responseMessage",Message.REQNULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
		}
		return callback;
	}
}
