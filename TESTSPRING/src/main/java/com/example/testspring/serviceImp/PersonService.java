package com.example.testspring.serviceImp;

import com.example.testspring.entity.Person;
import com.example.testspring.repository.CourseRepo;
import com.example.testspring.repository.ExerciceRepo;
import com.example.testspring.repository.PersonRepo;
import com.example.testspring.service.IPerson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService implements IPerson {
    PersonRepo personRepo;
    CourseRepo courseRepo;
    ExerciceRepo exerciceRepo;
@Override
    public Person addPerson(Person person) {
        return personRepo.save(person);
    }
}
