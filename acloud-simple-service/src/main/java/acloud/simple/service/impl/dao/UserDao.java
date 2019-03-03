package acloud.simple.service.impl.dao;

import java.util.List;

import acloud.simple.service.data.User;


public interface UserDao {

	List<User> findAll();
	User findById(String id);
}
