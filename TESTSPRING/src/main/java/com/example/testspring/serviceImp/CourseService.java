package com.example.testspring.serviceImp;

import com.example.testspring.entity.Course;
import com.example.testspring.entity.Person;
import com.example.testspring.repository.CourseRepo;
import com.example.testspring.repository.ExerciceRepo;
import com.example.testspring.repository.PersonRepo;
import com.example.testspring.service.ICourse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService implements ICourse {
    PersonRepo personRepo;
    CourseRepo courseRepo;
    ExerciceRepo exerciceRepo;
    @Override
    public Course addCourseAssignCoach(Course course, Long personID) {
        Person p=personRepo.findById(personID).orElse(null);
        course.setPerson(p);
        return courseRepo.save(course);

    }
}
