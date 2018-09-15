package com.kaloyantodorov.spring.boot.tutorial.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ExampleServiceImpl implements IExampleService {

    private Logger log = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    @PostConstruct
    public void print() {
        log.debug("ExampleServiceImpl");
    }

    @Override
    public String getClassName() {
        return this.getClass().getName();
    }
}
