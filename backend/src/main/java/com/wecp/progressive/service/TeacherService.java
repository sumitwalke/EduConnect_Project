package com.wecp.progressive.service;

import com.wecp.progressive.dto.TeacherDTO;
import com.wecp.progressive.entity.Teacher;

import java.util.List;

public interface TeacherService {

    public List<Teacher> getAllTeachers() throws Exception;

    public Integer addTeacher(Teacher teacher) throws Exception;

    public List<Teacher> getTeacherSortedByExperience() throws Exception;

    default void emptyArrayList() {
    }

    
    default public void updateTeacher(Teacher teacher) throws Exception { }

    default public void deleteTeacher(int teacherId) throws Exception { }

    default Teacher getTeacherById(int teacherId) throws Exception { return null; }

    
    default public void modifyTeacherDetails(TeacherDTO teacherDTO) throws Exception { }
}
