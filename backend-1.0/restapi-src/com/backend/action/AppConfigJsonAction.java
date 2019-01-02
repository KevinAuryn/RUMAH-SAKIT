package com.backend.action;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.backend.dao.BackendDAOFactory;
import com.backend.dao.BackendDAOFactoryHibernate;
import com.backend.other.Message;
import com.backend.other.RequestList;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;


public class AppConfigJsonAction  extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	HashMap<String, Object> request;
	HashMap<String, Object> callback;
	List<RequestList> requestList;
	
	BackendDAOFactory backendDAOFactory = BackendDAOFactory.instance(BackendDAOFactoryHibernate.class);
	
	public String insertConfig(){
	
		callback = new HashMap<String, Object>();
		logger.info("[START] AppConfigJsonAction_insertConfig.action ");
		 Session session = null;
		 Transaction transaction = null;
			try {
				session = backendDAOFactory.getAppConfigDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				transaction = session.beginTransaction();
				callback = backendDAOFactory.getAppConfigDAO().insertAppConfig(session, request);
				
				if(callback.get("responseStatus")!=null&&callback.get("responseStatus").toString().equalsIgnoreCase(Message.SUCCESS)){
					transaction.commit();
				}else{
					if(transaction!=null)transaction.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(transaction!=null)transaction.rollback();
				callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
				callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
			}finally {
				if(session!=null)session.close();
			}
			logger.info("[END] AppConfigJsonAction_insertConfig.action ");
		return "insertConfigJsonSuccess";
	}
	
	public String updateConfig(){
		
		callback = new HashMap<String, Object>();
		//logger.info("[START] MobileConfigJsonAction_updateConfig.action ");
		 Session session = null;
		 Transaction transaction = null;
			try {
				session = backendDAOFactory.getAppConfigDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				transaction = session.beginTransaction();
				callback = backendDAOFactory.getAppConfigDAO().updateAppConfig(session, request);
				if(callback.get("responseStatus")!=null&&callback.get("responseStatus").toString().equalsIgnoreCase(Message.SUCCESS)){
					transaction.commit();
				}else{
					if(transaction!=null)transaction.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(transaction!=null)transaction.rollback();
				callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
				callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
			}finally {
				if(session!=null)session.close();
			}
			//logger.info("[END] MobileConfigJsonAction_updateConfig.action ");
		return "updateConfigJsonSuccess";
	}
	
	public String deleteConfig(){
		
		callback = new HashMap<String, Object>();
		//logger.info("[START] MobileConfigJsonAction_deleteConfig.action ");
		 Session session = null;
		 Transaction transaction = null;
			try {
				session = backendDAOFactory.getAppConfigDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				transaction = session.beginTransaction();
				callback = backendDAOFactory.getAppConfigDAO().deleteAppConfig(session, request);
				if(callback.get("responseStatus")!=null&&callback.get("responseStatus").toString().equalsIgnoreCase(Message.SUCCESS)){
					transaction.commit();
				}else{
					if(transaction!=null)transaction.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				if(transaction!=null)transaction.rollback();
				callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
				callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
			}finally {
				if(session!=null)session.close();
			}
			//logger.info("[END] MobileConfigJsonAction_deleteConfig.action ");
		return "deleteConfigJsonSuccess";
	}
	
	
	public String testList(){
		callback = new HashMap<String, Object>();
		
		try {
			//callback = backendDAOFactory.getAppConfigDAO().sendRequest(request, requestList);
			
			//	callback.put("responseStatus",Message.SUCCESS);
			//	callback.put("responseMessage",Message.SUCCESS);
			 
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}
		
		return "testListJsonSuccess";
	}
	
	public String requestToSMMF(){
		
		callback = new HashMap<String, Object>();
		
		try {
			//callback = backendDAOFactory.getAppConfigDAO().sendRequest(request, requestList);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}
		
		
		return "requestToSMMFJsonSuccess";
	}
	
	public String merchantRequest(){
		
		callback = new HashMap<String, Object>();
		
		try {
			 for(RequestList ls : requestList){
				 System.out.println("#### makakn nasi uduk :: "+ls.getFirstName());
			 }
			 
			// String balikan ="";
						 
			 callback.put("responseStatus",Message.SUCCESS);
			 callback.put("responseMessage",Message.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",e.getMessage());
		}
		
		return "merchantRequestJsonSuccess";
	}
	
	
	
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

	public void setRequestList(List<RequestList> requestList) {
		this.requestList = requestList;
	}


}
