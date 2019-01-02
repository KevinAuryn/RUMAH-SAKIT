package com.mpe.basic.model.other;

import java.io.Serializable;

public class BranchTypeList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long branchTypeId;
	String parent;
	String name;
	
	public long getBranchTypeId() {
		return branchTypeId;
	}
	public void setBranchTypeId(long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
