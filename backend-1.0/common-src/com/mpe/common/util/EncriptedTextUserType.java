package com.mpe.common.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import org.jasypt.util.text.BasicTextEncryptor;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class EncriptedTextUserType implements UserType {

	private BasicTextEncryptor encryptor;
	
	public EncriptedTextUserType() {
		encryptor = new BasicTextEncryptor();
		encryptor.setPassword("nalimcasnafuka");
	}
	
	@Override
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		return arg0;
	}

	@Override
	public Object deepCopy(Object arg0) throws HibernateException {
		return arg0;
	}

	@Override
	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) arg0;
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		return arg0.equals(arg1);
	}

	@Override
	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return arg0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class returnedClass() {
		return String.class;
	}

	@Override
	public int[] sqlTypes() {
		return new  int[]{StringType.INSTANCE.sqlType()};
	}

	@Override
	public Object nullSafeGet(ResultSet arg0, String[] arg1,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		String value = arg0.getString(arg1[0]);
		String result = "";
		try {
			result = encryptor.decrypt(value);
		}catch (Exception e) {
		}
		return result;
	}

	@Override
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2,
			SessionImplementor arg3) throws HibernateException, SQLException {
		if (arg1 == null) {
			arg0.setNull(arg2, StringType.INSTANCE.sqlType());
		} else {
			String result = encryptor.encrypt((String) arg1);
			arg0.setString(arg2, result);
		}
		
	}
	
	

}
