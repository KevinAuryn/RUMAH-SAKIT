package com.backend.action;

import java.util.HashMap;

import org.hibernate.Session;

import com.backend.dao.BackendDAOFactory;
import com.backend.dao.BackendDAOFactoryHibernate;
import com.backend.other.Message;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;


public class UserAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	HashMap<String, Object> request;
	HashMap<String, Object> callback;
	
	BackendDAOFactory backendDAOFactory = BackendDAOFactory.instance(BackendDAOFactoryHibernate.class);
	Session session = null;
	public String login() throws Exception{
		logger.info("[START] UserAction_login.action ");
		try {
			session = backendDAOFactory.getUsersDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			callback = backendDAOFactory.getUsersDAO().getUsers(session, request);
			
		}catch(Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}finally {
			if(session!=null)session.close();
		}
		logger.info("[END] UserAction_login.action ");
		return "loginRequestJsonSuccess";
	}
	
	/*public String sendToKBIJ(){
		
		return "partnerRequestJsonSuccess";
	}*/
	
	@Override
	public String list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String partialList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String save() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String edit() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String detail() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String cancel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setRequest(HashMap<String, Object> request) {
		this.request = request;
	}

	public HashMap<String, Object> getCallback() {
		return callback;
	}

}
