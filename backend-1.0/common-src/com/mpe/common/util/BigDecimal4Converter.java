package com.mpe.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimal4Converter extends StrutsTypeConverter {
	Log log = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		String string = arg1[0].replaceAll(",", "");
		try {
			return new BigDecimal(string);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		DecimalFormat decimalFormat = new DecimalFormat("0.0000");
		return decimalFormat.format(arg1);
	}
	
	

}
