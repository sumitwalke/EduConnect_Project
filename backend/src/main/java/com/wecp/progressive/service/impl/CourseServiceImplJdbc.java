package com.wecp.progressive.service.impl;

import com.wecp.progressive.dao.CourseDAO;
import com.wecp.progressive.entity.Course;
import com.wecp.progressive.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseServiceImplJdbc implements CourseService {

    private CourseDAO courseDAO;

    public CourseServiceImplJdbc(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        try {
            return courseDAO.getAllCourses();
        } catch (SQLException e) {
            throw new Exception("Error fetching all courses", e);
        }
    }

    @Override
    public Course getCourseById(int courseId) throws Exception {
        try {
            return courseDAO.getCourseById(courseId);
        } catch (Exception e) {
            throw new Exception("Error fetching course with ID " + courseId, e);
        }
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        try {
            return courseDAO.addCourse(course);
        } catch (SQLException e) {
            throw new Exception("Error adding course: " + course.getCourseName(), e);
        }
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        try {
            courseDAO.updateCourse(course);
        } catch (SQLException e) {
            throw new Exception("Error updating course with ID " + course.getCourseId(), e);
        }
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        try {
            courseDAO.deleteCourse(courseId);
        } catch (SQLException e) {
            throw new Exception("Error deleting course with ID " + courseId, e);
        }
    }
}