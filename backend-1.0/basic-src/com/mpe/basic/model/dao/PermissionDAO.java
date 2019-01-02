package com.mpe.basic.model.dao;

import java.util.List;

import com.mpe.basic.model.Permission;
import com.mpe.common.dao.GenericDAO;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface PermissionDAO extends GenericDAO<Permission, Long> {
	
	List<Permission> findByNullParent();

}
