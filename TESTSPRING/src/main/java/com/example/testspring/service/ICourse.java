package com.example.testspring.service;

import com.example.testspring.entity.Course;

public interface ICourse {
    public Course addCourseAssignCoach(Course course, Long personID);
}
