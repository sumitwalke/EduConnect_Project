package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    int addCourse(Course course) throws SQLException;
    Course getCourseById(int courseId) throws SQLException;
    void updateCourse(Course course) throws SQLException;
    void deleteCourse(int courseId) throws SQLException;
    List<Course> getAllCourses() throws SQLException;
}