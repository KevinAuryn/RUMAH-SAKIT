package com.mpe.common.interceptor;

import java.io.Serializable;

import com.mpe.common.util.HibernatePerformanceMonitor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HibernatePerformanceMonitorInterceptor extends AbstractInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String callingContext = "URL " + arg0.getProxy().getActionName();
        long monitorId = HibernatePerformanceMonitor.get().start();
        HibernatePerformanceMonitor.get().stop(monitorId, callingContext);
		return arg0.invoke();
	}

}
