package com.wecp.progressive.service.impl;

import com.wecp.progressive.dao.StudentDAO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class StudentServiceImplJdbc implements StudentService {

    private StudentDAO studentDAO;

    public StudentServiceImplJdbc(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException e) {
            throw new Exception("Error fetching all students", e);
        }
    }

    @Override
    public Integer addStudent(Student student) throws Exception {
        try {
            return studentDAO.addStudent(student);
        } catch (SQLException e) {
            throw new Exception("Error adding student: " + student.getFullName(), e);
        }
    }

    @Override
    public List<Student> getAllStudentSortedByName() throws Exception {
        try {
            List<Student> sortedStudents = studentDAO.getAllStudents();
            if (!sortedStudents.isEmpty()) {
                sortedStudents.sort(Comparator.comparing(Student::getFullName));
            }
            return sortedStudents;
        } catch (SQLException e) {
            throw new Exception("Error fetching students sorted by Name ", e);
        }
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            throw new Exception("Error updating student with ID " + student.getStudentId(), e);
        }
    }

    @Override
    public void deleteStudent(int studentId) throws Exception {
        try {
            studentDAO.deleteStudent(studentId);
        } catch (SQLException e) {
            throw new Exception("Error deleting student with ID " + studentId, e);
        }
    }

    @Override
    public Student getStudentById(int studentId) throws Exception {
        try {
            return studentDAO.getStudentById(studentId);
        } catch (Exception e) {
            throw new Exception("Error fetching student with ID " + studentId, e);
        }
    }
}