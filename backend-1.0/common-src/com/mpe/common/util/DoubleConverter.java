package com.mpe.common.util;

import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

public class DoubleConverter extends StrutsTypeConverter {
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String string = values[0].replaceAll(",", "");
		//log.info("string = "+string);
		try {
			return Double.parseDouble(string.trim());
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return decimalFormat.format(arg1);
	}
	
	

}
