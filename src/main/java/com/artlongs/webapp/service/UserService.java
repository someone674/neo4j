package com.artlongs.webapp.service;

import com.artlongs.webapp.dao.UserDao;
import com.artlongs.webapp.dao.WorldDao;
import com.artlongs.webapp.model.User;
import com.artlongs.webapp.model.World;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public long countUsers() {
		return userDao.count();
	}
	
	public User createUser(String name,String birthday) {
		return userDao.save(new User(name,birthday));
	}
	
	public Iterable<User> getAllUsers() {
		return userDao.findAll();
	}
	
	public User findWorldById(Long id) {
		return userDao.findOne(id);
	}
	
	public User findWorldByName(String name) {
		return userDao.findBySchemaPropertyValue("name", name);
	}
	
	public Iterable<User> findAllByAge(int age) {
		return userDao.findAllBySchemaPropertyValue("age", age);
	}
	
	public Collection<User> makeSomeUsers() {
		Collection<User> users = new ArrayList<User>();
        users.add(createUser("leeton","1978-01-01"));
        users.add(createUser("neo4j", "2010-01-10"));


        
        return users;
	}

}
