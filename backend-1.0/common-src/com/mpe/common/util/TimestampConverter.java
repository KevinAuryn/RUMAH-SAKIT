package com.mpe.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class TimestampConverter extends StrutsTypeConverter {
	Log log = LogFactory.getFactory().getInstance(this.getClass());
	
	public static final String formFormat = "dd/MM/yyyy HH:mm";
	public static final String detailFormat = "dd MMM yyyy HH:mm";

	@Override
	public Object convertFromString(Map arg0, String[] value, Class arg2) {
		if (value[0] == null || value[0].trim().equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formFormat);
        try {
            return sdf.parse(value[0]);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		if (arg1!=null) {
			//log.info("Timestamp convert ");
			SimpleDateFormat sdf = new SimpleDateFormat(detailFormat);
	        return sdf.format(arg1);
		}
		return null;
	}
	
	

}
