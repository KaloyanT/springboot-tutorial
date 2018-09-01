package com.kaloyantodorov.spring.boot.tutorial.repository;

import com.kaloyantodorov.spring.boot.tutorial.domain.model.Child;
import com.kaloyantodorov.spring.boot.tutorial.domain.model.Parent;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ParentRepository extends CrudRepository<Parent, Long> {

    List<Parent> findAll();

    Parent findByParentID(Long id);
  
    @Query("SELECT p FROM Parent p WHERE p.parentID = :id AND p.property = :property")
    Parent findByParentIDAnAndPropertyWithCustomQuery(@Param("id") Long id, @Param("property") String property);
    
    // Native Query is also possible
    @Cacheable(value = "childrenForParent")
    @Query("SELECT p.children FROM Parent p JOIN p.children WHERE p.parentID = :id")
    Set<Child> getChildrenForParent(@Param("id") Long id);
}
