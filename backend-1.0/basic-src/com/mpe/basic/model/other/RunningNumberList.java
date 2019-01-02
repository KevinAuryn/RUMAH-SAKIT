package com.mpe.basic.model.other;

import java.io.Serializable;

public class RunningNumberList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long uniqueNumberId;
	long runningNumberId;
	String startNumber;
	String name;
	long lookupId;
	
	public long getUniqueNumberId() {
		return uniqueNumberId;
	}
	public void setUniqueNumberId(long uniqueNumberId) {
		this.uniqueNumberId = uniqueNumberId;
	}
	
	public long getRunningNumberId() {
		return runningNumberId;
	}
	public void setRunningNumberId(long runningNumberId) {
		this.runningNumberId = runningNumberId;
	}
	public String getStartNumber() {
		return startNumber;
	}
	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getLookupId() {
		return lookupId;
	}
	public void setLookupId(long lookupId) {
		this.lookupId = lookupId;
	}

	
}
