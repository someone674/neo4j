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
import java.util.List;
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
    private Integer bioLable;
    private Integer loveLable;

    private Integer area;
    private Integer city;

    private Integer salary;
    private Integer job;

    private Set<Enums.Finding> findingType;    //交往趋向

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Enums.Marryage getMarriage() {
        return marriage;
    }

    public void setMarriage(Enums.Marryage marriage) {
        this.marriage = marriage;
    }

    public Enums.BooldType getBloodType() {
        return bloodType;
    }

    public void setBloodType(Enums.BooldType bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getBioLable() {
        return bioLable;
    }

    public void setBioLable(Integer bioLable) {
        this.bioLable = bioLable;
    }

    public Integer getLoveLable() {
        return loveLable;
    }

    public void setLoveLable(Integer loveLable) {
        this.loveLable = loveLable;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public Set<Enums.Finding> getFindingType() {
        return findingType;
    }

    public void setFindingType(Enums.Finding finding) {
        Set<Enums.Finding> findings = getFindingType();
        findings.add(finding);
        this.findingType = findings;
    }

    public Integer getMobileActivation() {
        return mobileActivation;
    }

    public void setMobileActivation(Integer mobileActivation) {
        this.mobileActivation = mobileActivation;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public Integer getMailActivation() {
        return mailActivation;
    }

    public void setMailActivation(Integer mailActivation) {
        this.mailActivation = mailActivation;
    }

    public Date getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(Date loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
