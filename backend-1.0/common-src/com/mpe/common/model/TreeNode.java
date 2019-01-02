package com.mpe.common.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;	
	String description1;
	String description2;
	String description3;
	
	List<TreeNode> childs = new LinkedList<TreeNode>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode> getChilds() {
		return childs;
	}

	public void setChilds(List<TreeNode> childs) {
		this.childs = childs;
	}

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	
	
	
	
	
		

}
