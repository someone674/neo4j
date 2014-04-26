package com.artlongs.webapp.repository;

import com.artlongs.webapp.model.World;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

public interface WorldRepository extends GraphRepository<World> {}
