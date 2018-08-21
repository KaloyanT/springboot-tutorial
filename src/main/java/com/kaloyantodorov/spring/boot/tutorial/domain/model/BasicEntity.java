package com.kaloyantodorov.spring.boot.tutorial.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "BasicEntity")
@Table(name = "basic_entity")
public class BasicEntity {

    @Id
    @GenericGenerator(name = "basicEntityIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "basicEntityIdGenerator")
    @Column(name = "ID")
    private Long basicEntityID;

    @Basic(optional = false)
    @Column(name = "property")
    private String property;

    @Basic(optional = false)
    @Column(name = "property2")
    private String property2;

    public Long getBasicEntityID() {
        return basicEntityID;
    }

    public void setBasicEntityID(Long basicEntityID) {
        this.basicEntityID = basicEntityID;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @JsonIgnore
    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }
}
