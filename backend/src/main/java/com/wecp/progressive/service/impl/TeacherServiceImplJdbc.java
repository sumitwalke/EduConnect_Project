package com.wecp.progressive.service.impl;

import com.wecp.progressive.dao.TeacherDAO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class TeacherServiceImplJdbc implements TeacherService {

    private TeacherDAO teacherDAO;

    public TeacherServiceImplJdbc(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() throws Exception {
        try {
            return teacherDAO.getAllTeachers();
        } catch (SQLException e) {
            throw new Exception("Error fetching all teachers", e);
        }
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws Exception {
        try {
            return teacherDAO.addTeacher(teacher);
        } catch (SQLException e) {
            throw new Exception("Error adding teacher: " + teacher.getFullName(), e);
        }
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        try {
            List<Teacher> sortedTeachers = teacherDAO.getAllTeachers();
            if (!sortedTeachers.isEmpty()) {
                sortedTeachers.sort(Comparator.comparing(Teacher::getYearsOfExperience));
            }
            return sortedTeachers;
        } catch (SQLException e) {
            throw new Exception("Error fetching teachers sorted by Years of Experience ", e);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        try {
            teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            throw new Exception("Error updating teacher with ID " + teacher.getTeacherId(), e);
        }
    }

    @Override
    public void deleteTeacher(int teacherId) throws Exception {
        try {
            teacherDAO.deleteTeacher(teacherId);
        } catch (SQLException e) {
            throw new Exception("Error deleting teacher with ID " + teacherId, e);
        }
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws Exception {
        try {
            return teacherDAO.getTeacherById(teacherId);
        } catch (Exception e) {
            throw new Exception("Error fetching teacher with ID " + teacherId, e);
        }
    }
}