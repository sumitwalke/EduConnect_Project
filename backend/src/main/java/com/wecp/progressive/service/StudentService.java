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

    //Do not implement these methods in StudentServiceImplArraylist.java class
    default void updateStudent(Student student) throws Exception {}

    default void deleteStudent(int studentId) throws Exception {}

    default Student getStudentById(int studentId) throws Exception {
        return null;
    }

    //Do not implement these methods in StudentServiceImplArraylist.java and StudentServiceImplJdbc.java class
    //Do not implement in StudentServiceImplJpa.java until Day-13
    default public void modifyStudentDetails(StudentDTO studentDTO) throws Exception { }
}
