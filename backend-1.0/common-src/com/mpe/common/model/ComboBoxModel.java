package com.mpe.common.model;

import java.io.Serializable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class ComboBoxModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long id;
	String name;
	
	public ComboBoxModel() {
	}

	public ComboBoxModel(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
