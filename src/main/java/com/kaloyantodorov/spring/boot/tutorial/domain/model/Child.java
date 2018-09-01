package com.kaloyantodorov.spring.boot.tutorial.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Child")
@Table(name = "child")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Child {
	
	@Id
	@GenericGenerator(name = "childIdGenerator", strategy = "increment")
	@GeneratedValue(generator = "childIdGenerator")
	@Column(name = "childID")
	private Long childID;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	
	@Basic(optional = false)
	@NotNull
    @Min(value = 1)
	@Max(value = 120)
	private Integer age;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_parentID")
	private Parent parent;
	
	public Long getChildID() {
		return childID;
	}

	public void setChildID(Long childID) {
		this.childID = childID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((childID == null) ? 0 : childID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Child other = (Child) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (childID == null) {
			if (other.childID != null)
				return false;
		} else if (!childID.equals(other.childID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Child [childID=" + childID + ", name=" + name + ", age=" + age + ", parent=" + parent + "]";
	}
	 
}
