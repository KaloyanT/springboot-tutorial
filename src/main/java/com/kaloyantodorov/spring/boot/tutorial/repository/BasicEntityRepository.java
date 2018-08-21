package com.kaloyantodorov.spring.boot.tutorial.repository;

import com.kaloyantodorov.spring.boot.tutorial.domain.model.BasicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasicEntityRepository extends CrudRepository<BasicEntity, Long> {

    List<BasicEntity> findAll();

    BasicEntity findByBasicEntityID(Long id);

    @Query("SELECT be FROM BasicEntity be WHERE be.basicEntityID = :id AND be.property = :property")
    BasicEntity findByBasicEntityIDAnAndPropertyWithCustomQuery(@Param("id") Long id, @Param("property") String property);
}
