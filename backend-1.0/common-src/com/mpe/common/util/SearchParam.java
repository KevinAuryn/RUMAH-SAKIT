package com.mpe.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class SearchParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String partialListActionName;
	Map<String,Object> params;

	public SearchParam() {
		this.params = new HashMap<String, Object>();
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getPartialListActionName() {
		return partialListActionName;
	}

	public void setPartialListActionName(String partialListActionName) {
		this.partialListActionName = partialListActionName;
	}
	
	
}