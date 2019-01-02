package com.backend.dao;

import java.util.HashMap;

import org.hibernate.Session;

import com.mpe.common.dao.GenericDAO;
import com.backend.model.Users;

public interface UsersDAO extends GenericDAO<Users, Long> {

	public HashMap<String, Object> getUsers(Session session, HashMap<String, Object> request) throws Exception;

}
