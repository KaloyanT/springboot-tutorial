package com.kaloyantodorov.spring.boot.tutorial.service;

import com.kaloyantodorov.spring.boot.tutorial.domain.model.Child;
import com.kaloyantodorov.spring.boot.tutorial.domain.model.Parent;
import com.kaloyantodorov.spring.boot.tutorial.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ParentService {

    private ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public List<Parent> getParents() {
        return this.parentRepository.findAll();
    }

    public Parent getParentById(Long id) {
        return this.parentRepository.findByParentID(id);
    }

    public Parent getParentByIdAndParam(Long id, String property) {
        return this.parentRepository.findByParentIDAnAndPropertyWithCustomQuery(id, property);
    }
    
    public Set<Child> getChildrenForParent(Long id) {
    	return this.parentRepository.getChildrenForParent(id);
    }
    
    public void saveParent(Parent parent) {
    	
    	for(Child c : parent.getChildren()) {
    		parent.addChild(c);
    	}
    	
    	this.parentRepository.save(parent);
    }
    
    public void updateParent(Long id, Parent parent) {
    	
    	Parent currentParent = this.parentRepository.findByParentID(id);
    	
    	if (currentParent == null) {
    		throw new NoSuchElementException("A Parent with ID " + id + " can't be found");
    	}
    	
    	// Validate the parameters of the Parent first..
    	currentParent.setName(parent.getName());
    	currentParent.setProperty(parent.getProperty());
    	this.parentRepository.save(currentParent);
	}
    
    public void deleteParent(Long id) {
    	this.parentRepository.deleteById(id);
    }
    
    public void deleteParent(Parent parent) {
    	this.parentRepository.delete(parent);
    }
}
