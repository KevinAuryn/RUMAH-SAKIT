package com.mpe.common.util;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter {
	
	Log log = LogFactory.getFactory().getInstance(this.getClass());
	//SessionFactory sf;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		super.init(arg0);
		new HibernateUtil();
	}
	
	


}
