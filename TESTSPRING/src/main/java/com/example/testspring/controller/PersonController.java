package com.example.testspring.controller;

import com.example.testspring.entity.Person;
import com.example.testspring.repository.PersonRepo;
import com.example.testspring.service.IPerson;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {
    IPerson personService;
    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
}
