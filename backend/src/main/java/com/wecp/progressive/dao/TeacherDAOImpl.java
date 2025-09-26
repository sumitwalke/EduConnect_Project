package com.wecp.progressive.dao;


import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {


    @Override
    public int addTeacher(Teacher teacher) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO teacher (full_name, subject, contact_number, email, years_of_experience) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, teacher.getFullName());
            statement.setString(2, teacher.getSubject());
            statement.setString(3, teacher.getContactNumber());
            statement.setString(4, teacher.getEmail());
            statement.setInt(5, teacher.getYearsOfExperience());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                teacher.setTeacherId(generatedID); 
            }
        } catch (SQLException e) {
            System.err.println("Error adding teacher: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return generatedID;
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM teacher WHERE teacher_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teacherId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                String subject = resultSet.getString("subject");
                String contactNumber = resultSet.getString("contact_number");
                String email = resultSet.getString("email");
                int yearsOfExperience = resultSet.getInt("years_of_experience");

                return new Teacher(teacherId, fullName, subject, contactNumber, email, yearsOfExperience);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching teacher by ID: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return null; 
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE teacher SET full_name = ?, subject = ?, contact_number = ?, email = ?, years_of_experience =? WHERE teacher_id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, teacher.getFullName());
            statement.setString(2, teacher.getSubject());
            statement.setString(3, teacher.getContactNumber());
            statement.setString(4, teacher.getEmail());
            statement.setInt(5, teacher.getYearsOfExperience());
            statement.setInt(6, teacher.getTeacherId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating teacher: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM teacher WHERE teacher_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teacherId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting teacher: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teacherList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM teacher";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int teacherId = resultSet.getInt("teacher_id");
                String fullName = resultSet.getString("full_name");
                String subject = resultSet.getString("subject");
                String contactNumber = resultSet.getString("contact_number");
                String email = resultSet.getString("email");
                int yearsOfExperience = resultSet.getInt("years_of_experience");

                teacherList.add(new Teacher(teacherId, fullName, subject, contactNumber, email, yearsOfExperience));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all teachers: " + e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return teacherList;
    }
}
