package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Course;

import java.util.List;

public interface CourseDAO {
    int addCourse(Course course);
    Course getCourseById(int courseId);
    void updateCourse(Course course);
    void deleteCourse(int courseId);
    List<Course> getAllCourses();
}