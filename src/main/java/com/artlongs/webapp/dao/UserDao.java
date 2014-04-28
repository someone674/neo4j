package com.artlongs.webapp.dao;

import com.artlongs.webapp.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserDao extends GraphRepository<User> {}
