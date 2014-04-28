package com.artlongs.webapp.dao;

import com.artlongs.webapp.model.World;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface WorldDao extends GraphRepository<World> {}
