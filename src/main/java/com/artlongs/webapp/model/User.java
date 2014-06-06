package com.artlongs.webapp.model;

import com.artlongs.common.*;
import com.artlongs.base.model.BaseEntity;
import com.artlongs.common.Enums;
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

    @Indexed
    private Date birthday;

    @Indexed
    private int age;
    @Indexed
    private Integer gender;
    private Integer weight;               //体重
    private Integer height;               //身高

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_idnumber")
    private String idNumber;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_email")
    private String email;

    @Indexed
    private String mobile;

    private String homepage;              //个人主页

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_qq")
    private String qq;                    //QQ

    @Indexed
    private Integer degree;

    @Indexed(indexType = IndexType.FULLTEXT, indexName = "idx_school")
    private String school;

    private Enums.Marryage marriage;
    private Enums.BooldType bloodType;
    private Enums.BioTitle selfTitle;
    private Enums.BooldType loveTitle;

    private Integer area;
    private Integer city;

    private Integer salary;
    private Integer job;

    private Integer mobileActivation;     //手机激活：0：未激活；1：已激活；
    private Integer identification;       //实名认证：0：未认证；1：已认证；
    private Integer mailActivation;       //邮件激活：0：未激活；1：已激活；

    private Date loginIp;                 //登录IP
    private Date loginDate;               //登录时间

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
