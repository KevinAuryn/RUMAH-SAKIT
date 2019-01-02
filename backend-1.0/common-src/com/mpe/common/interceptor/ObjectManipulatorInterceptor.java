package com.mpe.common.interceptor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.backend.model.Users;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.Constants;
import com.mpe.common.util.SearchParam;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class ObjectManipulatorInterceptor extends AbstractInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {		
		Map<String, Object> parameter = actionInvocation.getInvocationContext().getParameters();
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		ValueStack valueStack = actionInvocation.getInvocationContext().getValueStack();
		Users user = (Users)session.get(Constants.USER);
		String lastAction = (String)session.get(Constants.LAST_ACTION);
		session.put(Constants.LAST_ACTION, actionInvocation.getProxy().getActionName());
		Set<String> strings = parameter.keySet();
		Iterator<String> iterator = strings.iterator();
		SearchParam searchParam = null;
		/*
		 * if action-name same with last action-name => 
		 * 1. search first into SearchParam with ActionName if both have same name => update searchParam
		 * 2. if not at point 1, save all param into SearchParam
		 * 
		 * if action-name not same =>
		 * 1. remove/invalidate session searchParam
		 */
		if (!CommonUtil.isSameActionClass(lastAction, actionInvocation.getProxy().getActionName())) {
			try {
				//log.info("remove search-param");
				session.remove(Constants.SEARCH_PARAM);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// create new
		searchParam = (SearchParam)session.get(Constants.SEARCH_PARAM);
		if (searchParam==null) {
			searchParam = new SearchParam();
			searchParam.setPartialListActionName(actionInvocation.getProxy().getActionName());
		}
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			if (key.equalsIgnoreCase("createBy")) {
				String[] arr = (String[]) parameter.get(key);
				if (arr != null && arr.length > 0) {
					for (int i=0; i<arr.length; i++) {
						arr[i] = (user!=null?user.getUserName():"-");
					}
		            parameter.put(key, arr);
		        }
			}
			if (key.equalsIgnoreCase("changeBy")) {
				String[] arr = (String[]) parameter.get(key);
				if (arr != null && arr.length > 0) {
					for (int i=0; i<arr.length; i++) {
						arr[i] = (user!=null?user.getUserName():"-");
					}
		            parameter.put(key, arr);
		        }
			}
			try {
				// get param and save! => ONLY IN partialList FORM NOT REDIRECT URL
				if (actionInvocation.getProxy().getActionName().endsWith("partialList")) {
					String[] attr = (String[]) parameter.get(key);
					//log.info("Key save : "+key+" => "+attr[0]);
					if (key.equalsIgnoreCase("gotoPage") || key.equalsIgnoreCase("gotoPage2")) {
						int i = 0;
						try {
							i = Integer.parseInt(attr[0]);
						} catch (Exception e) {
						}
						searchParam.getParams().put(key, i);
					} else searchParam.getParams().put(key, attr[0]);
				}
			} catch (Exception e) {
			}

		}
		
		// save param
		if (searchParam!=null) {
			//log.info("size of search-param : "+searchParam.getParams().size());
			session.put(Constants.SEARCH_PARAM, searchParam);
		}
		
		// reload param
		if (actionInvocation.getProxy().getActionName().endsWith("partialList")) {
			searchParam = (SearchParam)session.get(Constants.SEARCH_PARAM);
			if (searchParam!=null) {
				Map<String, Object> map = searchParam.getParams();
				Set<String> keys = map.keySet();
				Iterator<String> iteratorKeys = keys.iterator();
				while (iteratorKeys.hasNext()) {
					String key = (String)iteratorKeys.next();
					//log.info("Key reload : "+key + " => " + map.get(key));
					try {
						valueStack.setValue(key, map.get(key));
					} catch (Exception e) {
					}
				}				
			}
		}

        return actionInvocation.invoke();
	}	
	

}
