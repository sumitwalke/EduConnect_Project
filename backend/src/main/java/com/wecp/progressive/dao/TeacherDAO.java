package com.wecp.progressive.dao;

import com.wecp.progressive.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    int addTeacher(Teacher teacher) throws SQLException;
    Teacher getTeacherById(int teacherId) throws SQLException;
    void updateTeacher(Teacher teacher) throws SQLException;
    void deleteTeacher(int teacherId) throws SQLException;
    List<Teacher> getAllTeachers() throws SQLException;
}
