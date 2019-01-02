package com.backend.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.model.AppConfig;
import com.backend.other.JSONService;
import com.backend.other.Message;
import com.backend.other.RequestList;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.util.CommonUtil;

public class AppConfigDAOHibernate extends GenericHibernateDAO<AppConfig, Long>
		implements AppConfigDAO {
	
	Logger logger = LoggerFactory.getLogger(AppConfigDAOHibernate.class.getSimpleName());
	AppConfig appConfig = new AppConfig();
	
	BackendDAOFactory backendDAOFactory = BackendDAOFactory.instance(BackendDAOFactoryHibernate.class);
	
	@Override
	public boolean validKey(String key) throws Exception {
		// TODO Auto-generated method stub
		try {
			if (key != null && key.length() > 0) {
				appConfig = backendDAOFactory.getAppConfigDAO()
						.findByCriteria(Restrictions.eq("key", key));
				if (appConfig == null) {
					throw new Exception("Key " + key + " not found!");
				} else
					return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return false;
	}


	@Override
	public AppConfig getConfig(Session session, String key) throws Exception {
		AppConfig ac = null;
		try {
			if (key != null && key.length() > 0) {
				ac = backendDAOFactory.getAppConfigDAO().findByCriteria(session, Restrictions.eq("key", key).ignoreCase());
				if (ac == null) {
					return null;
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ac;
	}
	
	@Override
	public AppConfig getConfigWithDefault(Session session, String key, String defaultValue) throws Exception {
		AppConfig mb = null;
		try {
			if (key != null && key.length() > 0) {
				mb = backendDAOFactory.getAppConfigDAO().findByCriteria(session, Restrictions.eq("key", key).ignoreCase());
				if (mb == null) {
					mb = new AppConfig();
					mb.setValue(defaultValue);
					return mb;
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mb;
	}
	
	
	@Override
	public HashMap<String, Object> insertAppConfig(Session session, HashMap<String, Object> request) throws Exception{
		HashMap<String, Object> callback = new HashMap<String, Object>();
		
		try {
		//	Integer version 		= CommonUtil.getRequestVal(request, "version")!=null?Integer.parseInt(request.get("version").toString()):null;;
			String key 				= CommonUtil.getRequestVal(request, "key");
			String value 			= CommonUtil.getRequestVal(request, "value");
			//String lang 			= CommonUtil.getRequestVal(request, "lang");
			
		//	Integer vDb = backendDAOFactory.getAppConfigDAO().getVersion(session, "VERSION");
			
		/*	if(lang == null || lang.isEmpty()){
				lang = Language.EN.toString();
			}*/
			
			if(key == null || key.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage",Message.KEY_EMPTY);
				return callback;
			}
			
			if(value == null || value.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage",Message.VALUE_EMPTY);
				return callback;
			}
			
			
			
			AppConfig appConfig = backendDAOFactory.getAppConfigDAO().getConfig(session, key);
			
			if(appConfig!=null){
				callback.put("responseStatus",Message.DUPLICATE);
				callback.put("responseMessage",Message.DUPLICATE);

			}else{
				appConfig = new AppConfig();
				appConfig.setKey(key);
				appConfig.setValue(value);
				backendDAOFactory.getAppConfigDAO().save(appConfig, session);
				callback.put("responseStatus",Message.SUCCESS);
				callback.put("responseMessage",Message.SUCCESS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",Message.RUNTIME_EXCEPTION);

		}
		
		return callback;
	
	}
	
	@Override
	public HashMap<String, Object> updateAppConfig(Session session, HashMap<String, Object> request) throws Exception{
		HashMap<String, Object> callback = new HashMap<String, Object>();
		
		try {
			//Integer version 		= CommonUtil.getRequestVal(request, "version")!=null?Integer.parseInt(request.get("version").toString()):null;;
			String key 				= CommonUtil.getRequestVal(request, "key");
			String value 			= CommonUtil.getRequestVal(request, "value");
			
		//	Integer vDb = mobileBackendDAOFactory.getMobileConfigDAO().getVersion(session, "VERSION");
			
		/*	if(lang == null || lang.isEmpty()){
				lang = Language.EN.toString();
			}
			
			if(version == null || !version.equals(vDb)){
				callback.put("responseStatus",mobileBackendDAOFactory.getMobileLanguageDAO().getMobileLanguage(session, lang, "RESPONSE_STATUS", "INCOMPATIBLE").getValue());
				callback.put("responseMessage",mobileBackendDAOFactory.getMobileLanguageDAO().getMobileLanguage(session, lang, "RESPONSE_MESSAGE", "INCOMPATIBLE").getValue());
				return callback;
			}*/
			
			if(key == null || key.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage",Message.KEY_EMPTY);
				return callback;
			}
			
			if(value == null || value.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage",Message.VALUE_EMPTY);
				return callback;
			}
			
			
			AppConfig appConfig = backendDAOFactory.getAppConfigDAO().findByCriteria(Restrictions.eq("key", key).ignoreCase());
			
			if(appConfig!=null){
				appConfig.setValue(value);
				backendDAOFactory.getAppConfigDAO().update(appConfig, session);
				callback.put("responseStatus",Message.SUCCESS);
				callback.put("responseMessage",Message.SUCCESS);
				
			}else{
				callback.put("responseStatus",Message.NOT_FOUND);
				callback.put("responseMessage",Message.NOT_FOUND);
			}
			
			return callback;
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
		}
		
		return callback;
	
	}
	
	@Override
	public HashMap<String, Object> deleteAppConfig(Session session, HashMap<String, Object> request) throws Exception{
		HashMap<String, Object> callback = new HashMap<String, Object>();
		
		try {
			Long configId 			= CommonUtil.getRequestVal(request, "configId")!=null?Long.parseLong(request.get("configId").toString()):null;;
			
			
			if(configId == null || configId == 0){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage",Message.CONFIG_ID);
				return callback;
			}
			
			
			AppConfig appConfig = backendDAOFactory.getAppConfigDAO().findById(configId, session);
			if(appConfig!=null){
				backendDAOFactory.getAppConfigDAO().delete(appConfig, session);
				callback.put("responseStatus",Message.SUCCESS);
				callback.put("responseMessage",Message.SUCCESS);
			}else{
				callback.put("responseStatus",Message.NOT_FOUND);
				callback.put("responseMessage",Message.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage",Message.RUNTIME_EXCEPTION);
		}
		
		return callback;
	
	}
	
	
	
	@Override
	public HashMap<String, Object> sendRequestKBIJ(HashMap<String, Object> request, List<RequestList> listRequest) throws Exception{
		 HashMap<String, Object> callback = new HashMap<String, Object>();
		 Session session = null;
		
		try {
					
			String providerUrl	= CommonUtil.getRequestVal(request, "providerUrl");
			String timeOut		= CommonUtil.getRequestVal(request, "timeOut");
			String readTimeOut	= CommonUtil.getRequestVal(request, "readTimeOut");
			String xmlRequest	= CommonUtil.getRequestVal(request, "xmlRequest");
			String kbijUsername	= CommonUtil.getRequestVal(request, "kbijUsername");
			String kbijPassword	= CommonUtil.getRequestVal(request, "kbijPassword");
			
			if(providerUrl == null || providerUrl.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","Provider Url is "+Message.EMPTY);
				return callback;
			}
			
			if(timeOut == null || timeOut.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","timeOut is "+Message.EMPTY);
				return callback;
			}
			
			if(xmlRequest == null || xmlRequest.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","XML Request is "+Message.EMPTY);
				return callback;
			}
			
			if(kbijUsername == null || kbijUsername.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","KBIJ Username is "+Message.EMPTY);
				return callback;
			}
			
			if(kbijPassword == null || kbijPassword.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","KBIJ Password is "+Message.EMPTY);
				return callback;
			}
			
			if(readTimeOut == null || readTimeOut.isEmpty()){
				callback.put("responseStatus",Message.EMPTY);
				callback.put("responseMessage","Read TimeOut is "+Message.EMPTY);
				return callback;
			}
			
			// String xml = generateRequestXml(listRequest);
			 callback = JSONService.kbijSend(providerUrl, timeOut, readTimeOut, xmlRequest, kbijUsername, kbijPassword);
			 
				 
		} catch (Exception e) {
			e.printStackTrace();
			
			callback.put("responseStatus",Message.RUNTIME_EXCEPTION);
			callback.put("responseMessage","Provider Send "+Message.RUNTIME_EXCEPTION);
		}finally {
			if(session!=null)session.close();
		}
		
		
		return callback;
	
	}
	
	
	public String generateReffNumber(SimpleDateFormat sdf, long partnerId, int runNo){
		String reffNumber = "", defaultStr="0000"+runNo;
		try {
			reffNumber = sdf.format(new Date())+"-"+partnerId+defaultStr.substring(defaultStr.length()-4,defaultStr.length());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reffNumber;
	}
}
