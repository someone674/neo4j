package com.artlongs.webapp.model;

import com.artlongs.framework.model.BaseEntity;
import org.apache.commons.lang.time.DateUtils;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;
import java.util.Set;

@NodeEntity
public class User extends   BaseEntity {
	private final static String FRIEND_TO_FRIEND = "FRIEND_TO_FRIEND";



    @Indexed
    private String name;

    @Indexed
    private Date birthday;

    @Indexed
    private int age;

    @Fetch
    @RelatedTo(type = FRIEND_TO_FRIEND, direction = Direction.BOTH)
    private Set<User> friend;

    public User() {
    }

    public User(String name) {
        this.name = name;
        if (this.birthday != null) {
            Year birthYear = Year.parse(this.birthday.toString());
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

    public Set<User> getFriend() {
        return friend;
    }

    public void setFriend(Set<User> friend) {
        this.friend = friend;
    }


	


}
