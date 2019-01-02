package com.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: MobileConfigEndPoint
 *
 */

@Entity
@Table(name = "APP_CONFIG")
public class AppConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "APP_CONFIG_SEQ", sequenceName = "APP_CONFIG_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_CONFIG_SEQ")
	@Column(name = "app_config_id", nullable=false)
	long appConfigId;

	@Column(name = "key", length = 128, nullable=false)
	String key;

	@Column(name = "value", length = 128)
	String value;

	public AppConfig() {
		super();
	}

	public long getAppConfigId() {
		return appConfigId;
	}


	public void setAppConfigId(long appConfigId) {
		this.appConfigId = appConfigId;
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}


