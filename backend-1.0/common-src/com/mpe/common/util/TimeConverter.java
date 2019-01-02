/**
 * 
 */
package com.mpe.common.util;

import java.lang.reflect.Member;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ognl.DefaultTypeConverter;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class TimeConverter extends DefaultTypeConverter {
	
	Log log = LogFactory.getFactory().getInstance(this.getClass());
	public static final String formFormat = "HH:mm";
	public static final String detailFormat = "HH:mm";

	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		Object o = null;
        if (value instanceof Object[]) {
            if (((Object[]) value).length > 0) {
            	o = ((Object[]) value)[0];
            }
        } else o = value;
		if (o == null) {
            return null;
		} else if (toType == Time.class) {
            return convertToDate(toType, o, formFormat);
		} else if (toType == String.class) {
            return convertToString(o);
		}
		throw new ConversionException("Could not convert " +  value.getClass().getName() + " to " +  toType.getName());
	}

	@Override
	public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
		return this.convertValue(context, value, toType);
	}
	
	protected Object convertToDate(Class type, Object value, String pattern) {
		DateFormat df = getFormater(pattern);
		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}
				Date date = df.parse((String) value);
				Time time = new Time(date.getTime());
			    return time;
			} catch (Exception pe) {
				pe.printStackTrace();
				throw new ConversionException("Error converting String to Date");
			}
		}
		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}

	protected Object convertToString(Object value) {
		if (value instanceof Date) {
			DateFormat df;
			if (value instanceof Time) {
				df = getFormater("HH:mm");
			} else {
			   df = getFormater("dd/MM/yyyy");
			}
            try {
                return df.format(value);
            } catch (Exception e) {
            	e.printStackTrace();
            	throw new ConversionException("Error converting Date to String");
            }
        }
        return value.toString();
	}

    /**
     * Returns DateFormat object from cache.
     *
     * @param pattern date/time formatting pattern
     * @return DateFormat
     */
    protected DateFormat getFormater(final String pattern) {
    	DateFormat tmpResult =  new SimpleDateFormat(pattern);
    	return tmpResult;
    }

}
