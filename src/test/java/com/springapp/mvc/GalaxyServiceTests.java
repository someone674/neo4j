package com.springapp.mvc;

import com.artlongs.webapp.model.World;
import com.artlongs.webapp.service.GalaxyService;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;

@ContextConfiguration(locations = "classpath:/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GalaxyServiceTests {

    @Autowired
    private GalaxyService galaxyService;

    @Autowired
    private Neo4jTemplate neo4jTemplate;

    @Rollback(false)
    @BeforeTransaction
    public void cleanUpGraph() {
        Neo4jHelper.cleanDb(neo4jTemplate);
    }

    @Test
    public void shouldAllowDirectWorldCreation() {
        assertEquals(0, galaxyService.getNumberOfWorlds());
        World myWorld = galaxyService.createWorld("mine", 0);
        assertEquals(1, galaxyService.getNumberOfWorlds());

        Iterable<World> foundWorlds = galaxyService.getAllWorlds();
        World mine = foundWorlds.iterator().next();
        assertEquals(myWorld.getName(), mine.getName());
    }

    @Test
    public void shouldHaveCorrectNumberOfWorlds() {
        galaxyService.makeSomeWorlds();
        assertEquals(13, galaxyService.getNumberOfWorlds());
    }

    @Test
    public void shouldFindWorldsById() {
        galaxyService.makeSomeWorlds();

        for (World world : galaxyService.getAllWorlds()) {
            World foundWorld = galaxyService.findWorldById(world.getId());
            assertNotNull(foundWorld);
        }
    }

    @Test
    public void shouldFindWorldsByName() {
        galaxyService.makeSomeWorlds();

        for (World world : galaxyService.getAllWorlds()) {
            World foundWorld = galaxyService.findWorldByName(world.getName());
            assertNotNull(foundWorld);
        }
    }

    @Test
    public void shouldReachMarsFromEarth() {
        galaxyService.makeSomeWorlds();

        World earth = galaxyService.findWorldByName("Earth");
        World mars = galaxyService.findWorldByName("Mars");

        assertTrue(mars.canBeReachedFrom(earth));
        assertTrue(earth.canBeReachedFrom(mars));
    }

    @Test
    public void shouldFindAllWorlds() {
        Collection<World> madeWorlds = galaxyService.makeSomeWorlds();
        Iterable<World> foundWorlds = galaxyService.getAllWorlds();

        int countOfFoundWorlds = 0;
        for (World foundWorld : foundWorlds) {
            assertTrue(madeWorlds.contains(foundWorld));
            countOfFoundWorlds++;
        }

        assertEquals(madeWorlds.size(), countOfFoundWorlds);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindWorldsWith1Moon() {
        galaxyService.makeSomeWorlds();

        for (World worldWithOneMoon : galaxyService.findAllByNumberOfMoons(1)) {
            assertThat(
                    worldWithOneMoon.getName(),
                    is(anyOf(containsString("Earth"), containsString("Midgard"))));
        }
    }

    @Test
    public void shouldNotFindKrypton() {
        galaxyService.makeSomeWorlds();
        World krypton = galaxyService.findWorldByName("Krypton");
        assertNull(krypton);
    }


    @Test
    public void buileFile() {

        String path = "d:/zzz";
        String fileName = "email.csv";
        File dir = FileUtils.getFile(path);
        File file = FileUtils.getFile(path, fileName);
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }

            if (!file.exists()) {
                Files.newWriter(file, Charsets.UTF_8);
                System.out.printf("file to create!");
            }
            String line = Files.readFirstLine(file, Charsets.UTF_8);
            if (StringUtils.isBlank(line)) {
                Files.append("fileTitle", file, Charsets.UTF_8);
                System.out.printf("readfirstLine=++++++++");
            } else {
                Files.append("content", file, Charsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
