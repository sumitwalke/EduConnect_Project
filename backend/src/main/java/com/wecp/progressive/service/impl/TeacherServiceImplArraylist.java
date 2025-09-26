package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherServiceImplArraylist implements TeacherService {

    private static List<Teacher> teacherList = new ArrayList<>();

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherList;
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        teacherList.add(teacher);
        return teacherList.size();
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        List<Teacher> teachers = teacherList;
        teachers.sort(Comparator.comparing(Teacher::getYearsOfExperience));
        return teachers;
    }

    @Override
    public void emptyArrayList() {
        teacherList = new ArrayList<>();
    }
}