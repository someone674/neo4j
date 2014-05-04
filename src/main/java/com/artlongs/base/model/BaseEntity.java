package com.artlongs.base.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.neo4j.annotation.GraphId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * BASE ENTITY
 * @author: leeton
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @GraphId
    private Long graphId;


    @Id
    @Column(length = 32, nullable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    private Date createDate;// 创建日期
    private Date modifyDate;// 修改日期


    @Override
    public int hashCode() {
        return id == null ? System.identityHashCode(this) : id.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        return obj instanceof BaseEntity && id.equals( ((BaseEntity) obj).getId() );
    }


    @Override
    public String toString() {
        return String.format("obj{graphId='%s'}", graphId);
    }

    // ============= getters & setters =============


    public Long getGraphId() {
        return graphId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
