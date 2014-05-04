package com.artlongs.webapp.model;

import com.artlongs.common.Constants;
import com.artlongs.base.model.BaseEntity;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User extends BaseEntity {

    @Indexed
    private String account; //login name

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_name")
    private String name; //display name

    private String password;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_email")
    private String email;

    @Indexed
    private Date birthday;

    @Indexed
    private int age;

    private Roles[] roles;

    @Fetch
    @RelatedTo(type = RelationshipTypes.FRIEND, direction = Direction.BOTH)
    private Set<User> friends =  new HashSet<User>();

    public User() {}

    public User(String name, String birthday) {
        this.name = name;
        if (birthday != null && birthday.length() > 0) {
            Year birthYear = Year.parse(birthday, DateTimeFormatter.ISO_LOCAL_DATE);
            if (Year.now().isAfter(birthYear)) {
                this.age = Year.now().getValue() - birthYear.getValue();
            }
        }

    }

    public User(String account, String name, String password, Roles... roles) {
        this.account = account;
        this.name = name;
        this.password = encode(password);
        this.roles = roles;
    }

    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, Constants.PASSWORD_SALT);
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

   public enum Roles implements GrantedAuthority {
       ROLE_USER,ROLE_ADMIN;

       @Override
       public String getAuthority() {
           return name();
       }

   }

    // =============== getter & setter =================

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Roles[] getRoles() {
        return roles;
    }

    public void setRoles(Roles[] roles) {
        this.roles = roles;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
