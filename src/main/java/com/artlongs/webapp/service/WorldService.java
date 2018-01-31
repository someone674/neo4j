package com.artlongs.webapp.service;

import com.artlongs.webapp.dao.WorldDao;
import com.artlongs.webapp.model.World;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class WorldService {
	
	@Resource
	private WorldDao worldDao;
	
	public long getNumberOfWorlds() {
		return worldDao.count();
	}
	
	public World createWorld(String name, int moons) {
		return worldDao.save(new World(name, moons));
	}
	
	public Iterable<World> getAllWorlds() {
		return worldDao.findAll();
	}
	
	public World findWorldById(Long id) {
		return worldDao.findOne(id);
	}
	
	public World findWorldByName(String name) {
		return worldDao.findBySchemaPropertyValue("name", name);
	}
	
	public Iterable<World> findAllByNumberOfMoons(int numberOfMoons) {
		return worldDao.findAllBySchemaPropertyValue("moons", numberOfMoons);
	}
	
	public Collection<World> makeSomeWorlds() {
		Collection<World> worlds = new ArrayList<World>();
				
		// Solar worlds		
		worlds.add(createWorld("Mercury", 0));
		worlds.add(createWorld("Venus", 0));
        
        World earth = createWorld("Earth", 1);        
        World mars = createWorld("Mars", 2);        
        mars.addRocketRouteTo(earth);
        worldDao.save(mars);
        worlds.add(earth);
        worlds.add(mars);
        
        worlds.add(createWorld("Jupiter", 63));
        worlds.add(createWorld("Saturn", 62));
        worlds.add(createWorld("Uranus", 27));
        worlds.add(createWorld("Neptune", 13));

        // Norse worlds
        worlds.add(createWorld("Alfheimr", 0));
        worlds.add(createWorld("Midgard", 1));
        worlds.add(createWorld("Muspellheim", 2));
        worlds.add(createWorld("Asgard", 63));
        worlds.add(createWorld("Hel", 62));
        
        return worlds;
	}

}
