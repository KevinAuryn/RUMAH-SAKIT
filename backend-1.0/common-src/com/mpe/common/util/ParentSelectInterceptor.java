package com.mpe.common.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class ParentSelectInterceptor extends AbstractInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {		
		Map<String, Object> map = actionInvocation.getInvocationContext().getParameters();
		Set<String> strings = map.keySet();
		String[] keyFound = new String[strings.size()];
		Iterator<String> iterator = strings.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
			// make sure you use '-APB-' in your headerKey select!
			if (getParameter(map, key)!=null && getParameter(map, key).equalsIgnoreCase("-APB-")) {
				keyFound[i] = key;
			} else {
				keyFound[i] = "";
			}
			i++;
		}
		// remove key
		for (int j=0; j<strings.size(); j++) {
			if (keyFound[j]!=null && keyFound[j].length()>0) map.remove(keyFound[j]);
		}
		
        return actionInvocation.invoke();
	}	
	
    private String getParameter(Map<String, Object> map, String key) {
        String[] arr = (String[]) map.get(key);
        if (arr != null && arr.length > 0) {
            return arr[0];
        }
        return null;
    }
	

}
