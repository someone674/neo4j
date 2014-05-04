package com.artlongs.webapp.model;

import com.artlongs.base.model.BaseEntity;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class World extends BaseEntity {
	private final static String REACHABLE_BY_ROCKET = "REACHABLE_BY_ROCKET";


    @Indexed
    private String name;

    @Indexed
    private int moons;

    @Fetch
    @RelatedTo(type = REACHABLE_BY_ROCKET, direction = Direction.BOTH)
    private Set<World> reachableByRocket;

    public World(String name, int moons) {
        this.name = name;
        this.moons = moons;
    }

    public World() {
    }



    public void addRocketRouteTo(World otherWorld) {
    	reachableByRocket.add(otherWorld);
    }
    
    public boolean canBeReachedFrom(World otherWorld) {
    	return reachableByRocket.contains(otherWorld);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoons() {
        return moons;
    }

    public void setMoons(int moons) {
        this.moons = moons;
    }



	
	@Override
    public String toString() {
        return String.format("World{name='%s', moons=%d}", name, moons);
    }
}
