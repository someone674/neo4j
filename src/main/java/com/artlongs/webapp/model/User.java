package com.artlongs.webapp.model;

import com.artlongs.framework.model.BaseEntity;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@NodeEntity
public class User extends BaseEntity {
    private final static String FRIEND_TO_FRIEND = "FRIEND_TO_FRIEND";

    @Indexed
    private String name;

    @Indexed
    private Date birthday;

    @Indexed
    private int age;

    @Fetch
    @RelatedTo(type = FRIEND_TO_FRIEND, direction = Direction.BOTH)
    private Set<User> friends;

    public User() {
    }

    public User(String name,String birthday) {
        this.name = name;
        if (birthday != null && birthday.length() > 0) {
            Year birthYear = Year.parse(birthday, DateTimeFormatter.ISO_LOCAL_DATE);
            if (Year.now().isAfter(birthYear)) {
                this.age = Year.now().getValue() - birthYear.getValue();
            }
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }


}
