package com.kaloyantodorov.spring.boot.tutorial.domain.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Parent")
@Table(name = "parent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Parent {

    @Id
    @GenericGenerator(name = "parentIdGenerator", strategy = "increment")
    @GeneratedValue(generator = "parentIdGenerator")
    @Column(name = "parentID")
    private Long parentID;

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 0, max = 30)
    @NotNull
    private String name;
    
    @Basic(optional = false)
    @Column(name = "property")
    @Size(min = 0, max = 100)
    private String property;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Child> children = new HashSet<>();
    
    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

	public Set<Child> getChildren() {
		return children;
	}

	public void setChildren(Set<Child> children) {
		this.children = children;
	}
    
	// Important
    public void addChild(Child child) {
    	this.children.add(child);
    	child.setParent(this);
    }
    
    public void removeChild(Child child) {
    	this.children.remove(child);
    	child.setParent(null);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentID == null) ? 0 : parentID.hashCode());
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		return result;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parent other = (Parent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentID == null) {
			if (other.parentID != null)
				return false;
		} else if (!parentID.equals(other.parentID))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parent [parentID=" + parentID + ", name=" + name + ", property=" + property + ", children=" + children
				+ "]";
	}
    
}
