package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public int addCourse(Course course) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO Course (course_name, description, teacher_id) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getTeacher().getTeacherId());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                course.setCourseId(generatedID); 
            }
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return generatedID;
    }

    @Override
    public Course getCourseById(int courseId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM course WHERE course_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String description = resultSet.getString("description");
                int teacherId = resultSet.getInt("teacher_id");

                return new Course(courseId, courseName, description, teacherId);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching course by ID: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return null; 
    }

    @Override
    public void updateCourse(Course course) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE course SET course_name = ?, description = ?, teacher_id = ? WHERE course_id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getDescription());
            statement.setInt(3, course.getTeacher().getTeacherId());
            statement.setInt(4, course.getCourseId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void deleteCourse(int courseId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM course WHERE course_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, courseId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courseList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM course";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                String description = resultSet.getString("description");
                int teacherId = resultSet.getInt("teacher_id");

                courseList.add(new Course(courseId, courseName, description, teacherId));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all courses: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return courseList;
    }
}
