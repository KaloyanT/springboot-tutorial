package com.kaloyantodorov.spring.boot.tutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaloyantodorov.spring.boot.tutorial.domain.model.Parent;
import com.kaloyantodorov.spring.boot.tutorial.service.ExampleServiceSecondImpl;
import com.kaloyantodorov.spring.boot.tutorial.service.IExampleService;
import com.kaloyantodorov.spring.boot.tutorial.service.ParentService;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent")
public class ParentController {

    private ParentService parentService;

    // @Autowired won't work here, because we have 2 implementations of our interface/Service
    // and Spring Boot cannot automatically pick an implementation to create the bean
    private IExampleService exampleService;

    public ParentController(ParentService parentService, ExampleServiceSecondImpl exampleServiceSecond) {
        this.parentService = parentService;
        this.exampleService = exampleServiceSecond;
    }

    @GetMapping("/service")
    public ResponseEntity<?> getService() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        response.put("serviceName", this.exampleService.getClassName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllParents() {
        return new ResponseEntity<>(this.parentService.getParents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getParentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.parentService.getParentById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/by-id-and-property", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getParentIdAndParam(@RequestParam("id") Long id, @RequestParam("property") String property) {
        return new ResponseEntity<>(this.parentService.getParentByIdAndParam(id, property), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/get-children/{id}", method = RequestMethod.GET) 
    public ResponseEntity<?> getChildrenForParent(@PathVariable("id") Long id) {
    	return new ResponseEntity<>(this.parentService.getChildrenForParent(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<?> insertParent(@RequestBody Parent parent) {
    	this.parentService.saveParent(parent);
    	return new ResponseEntity<>(parent, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateParent(@PathVariable("id") Long id, @RequestBody Parent parent) {
    	
    	try {
    		this.parentService.updateParent(id, parent);
    	} catch (NoSuchElementException e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteParent(@PathVariable("id") Long id) {
    	this.parentService.deleteParent(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

}
