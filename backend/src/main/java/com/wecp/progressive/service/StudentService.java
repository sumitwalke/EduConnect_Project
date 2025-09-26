package com.wecp.progressive.service;

import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents() throws Exception;

    Integer addStudent(Student student) throws Exception;

    List<Student> getAllStudentSortedByName() throws Exception;

    default void emptyArrayList() {
    }

    
    default void updateStudent(Student student) throws Exception {}

    default void deleteStudent(int studentId) throws Exception {}

    default Student getStudentById(int studentId) throws Exception {
        return null;
    }

   
    default public void modifyStudentDetails(StudentDTO studentDTO) throws Exception { }
}
