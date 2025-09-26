package com.wecp.progressive.dao;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int generatedID = -1;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "INSERT INTO student (full_name, date_of_birth, contact_number, email, address) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, student.getFullName());
            statement.setDate(2, new java.sql.Date(student.getDateOfBirth().getTime()));
            statement.setString(3, student.getContactNumber());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getAddress());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedID = resultSet.getInt(1);
                student.setStudentId(generatedID); 
            }
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return generatedID;
    }

    @Override
    public Student getStudentById(int studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM student WHERE student_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String fullName = resultSet.getString("full_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String contactNumber = resultSet.getString("contact_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new Student(studentId, fullName, dateOfBirth, contactNumber, email, address);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student by ID: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return null; 
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "UPDATE student SET full_name = ?, date_of_birth = ?, contact_number = ?, email = ?, address =? WHERE student_id = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, student.getFullName());
            statement.setDate(2, new java.sql.Date(student.getDateOfBirth().getTime()));
            statement.setString(3, student.getContactNumber());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getAddress());
            statement.setInt(6, student.getStudentId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void deleteStudent(int studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "DELETE FROM student WHERE student_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            throw e; 
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnectionManager.getConnection();
            String sql = "SELECT * FROM student";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String fullName = resultSet.getString("full_name");
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                String contactNumber = resultSet.getString("contact_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                studentList.add(new Student(studentId, fullName, dateOfBirth, contactNumber, email, address));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all students: " + e.getMessage());
            throw e; 
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return studentList;
    }
}
