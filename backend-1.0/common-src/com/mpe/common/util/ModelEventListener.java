package com.mpe.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class ModelEventListener implements LoadEventListener {
	
	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public void onLoad(LoadEvent event, LoadType type) throws HibernateException {
		//log.info("Event : "+event.getEntityClassName()+" // "+type.getName());
	}
	
	

}
