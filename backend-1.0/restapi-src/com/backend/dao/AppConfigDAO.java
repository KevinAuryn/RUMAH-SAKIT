package com.backend.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.backend.model.AppConfig;
import com.backend.other.RequestList;
import com.mpe.common.dao.GenericDAO;

public interface AppConfigDAO extends GenericDAO<AppConfig, Long> {

	public boolean validKey(String key) throws Exception;

	public AppConfig getConfig(Session session, String key) throws Exception;

	public AppConfig getConfigWithDefault(Session session, String key, String defaultValue) throws Exception;

	public HashMap<String, Object> insertAppConfig(Session session, HashMap<String, Object> request) throws Exception;

	public HashMap<String, Object> updateAppConfig(Session session, HashMap<String, Object> request) throws Exception;

	public HashMap<String, Object> deleteAppConfig(Session session, HashMap<String, Object> request) throws Exception;

	public HashMap<String, Object> sendRequestKBIJ(HashMap<String, Object> request, List<RequestList> listRequest)
			throws Exception;
	
}
