package com.kaloyantodorov.spring.boot.tutorial.service;

import com.kaloyantodorov.spring.boot.tutorial.domain.model.BasicEntity;
import com.kaloyantodorov.spring.boot.tutorial.repository.BasicEntityRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BasicEntityService {

    private BasicEntityRepository basicEntityRepository;

    public BasicEntityService(BasicEntityRepository basicEntityRepository) {
        this.basicEntityRepository = basicEntityRepository;
    }

    public List<BasicEntity> getBasicEntities() {
        return this.basicEntityRepository.findAll();
    }

    public BasicEntity getBasicEntityById(Long id) {
        return this.basicEntityRepository.findByBasicEntityID(id);
    }

    public BasicEntity getBasicEntityByIdAndParam2(Long id, String property) {
        return this.basicEntityRepository.findByBasicEntityIDAnAndPropertyWithCustomQuery(id, property);
    }
}
