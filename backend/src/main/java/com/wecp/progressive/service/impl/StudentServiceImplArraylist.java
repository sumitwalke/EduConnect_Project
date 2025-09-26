package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImplArraylist implements StudentService {

    private static List<Student> studentList = new ArrayList<>();

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public Integer addStudent(Student student) {
        studentList.add(student);
        return studentList.size();
    }

    @Override
    public List<Student> getAllStudentSortedByName() {
        List<Student> sortStudents = studentList;
        sortStudents.sort(Comparator.comparing(Student::getFullName));
        return sortStudents;
    }

    @Override
    public void emptyArrayList() {
        studentList = new ArrayList<>();
    }
}