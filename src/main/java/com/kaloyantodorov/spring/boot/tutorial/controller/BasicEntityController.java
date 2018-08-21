package com.kaloyantodorov.spring.boot.tutorial.controller;

import com.kaloyantodorov.spring.boot.tutorial.service.BasicEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-entity")
public class BasicEntityController {

    private BasicEntityService basicEntityService;

    public BasicEntityController(BasicEntityService basicEntityService) {
        this.basicEntityService = basicEntityService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllBasicEntities() {

        return new ResponseEntity<>(this.basicEntityService.getBasicEntities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getBasicEntityById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(this.basicEntityService.getBasicEntityById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/by-id-and-property", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getBasicEntityByIdAndParam2(@RequestParam("id") Long id, @RequestParam("property") String property) {
        return new ResponseEntity<>(this.basicEntityService.getBasicEntityByIdAndParam2(id, property), HttpStatus.OK);
    }

}
