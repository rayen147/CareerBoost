package com.example.testspring.controller;

import com.example.testspring.entity.Course;
import com.example.testspring.entity.Person;
import com.example.testspring.service.ICourse;
import com.example.testspring.service.IPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/courses")
@RestController
@Slf4j
public class CourseController {
    IPerson personService;
    ICourse courseService;
    @PostMapping("/addCourse{idPerson}")
    public Course addCourse(@RequestBody Course course, @PathVariable("idPerson") Long idPerson) {

        return courseService.addCourseAssignCoach(course,idPerson);
    }

}
