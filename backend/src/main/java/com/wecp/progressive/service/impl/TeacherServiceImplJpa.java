package com.wecp.progressive.service.impl;

import com.wecp.progressive.dto.TeacherDTO;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.exception.TeacherAlreadyExistsException;
import com.wecp.progressive.repository.CourseRepository;
import com.wecp.progressive.repository.EnrollmentRepository;
import com.wecp.progressive.repository.TeacherRepository;
import com.wecp.progressive.repository.UserRepository;
import com.wecp.progressive.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TeacherServiceImplJpa implements TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() throws Exception {
        return teacherRepository.findAll();
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws Exception {
        Teacher existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (existingTeacher != null) {
            throw new TeacherAlreadyExistsException("Teacher with this email already exists, Email: " + teacher.getEmail());
        }
        return teacherRepository.save(teacher).getTeacherId();
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        List<Teacher> sortedTeachers = teacherRepository.findAll();
        sortedTeachers.sort(Comparator.comparing(Teacher::getYearsOfExperience));
        return sortedTeachers;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        Teacher existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (existingTeacher != null) {
            throw new TeacherAlreadyExistsException("Teacher with this email already exists, Email: " + teacher.getEmail());
        }
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) throws Exception {
        userRepository.deleteByTeacherId(teacherId);
        enrollmentRepository.deleteByTeacherId(teacherId);
        courseRepository.deleteByTeacherId(teacherId);
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public Teacher getTeacherById(int teacherId) throws Exception {
        return teacherRepository.findByTeacherId(teacherId);
    }

    @Override
    public void modifyTeacherDetails(TeacherDTO teacherDTO) throws Exception {
        Teacher existingTeacher = teacherRepository.findByEmail(teacherDTO.getEmail());
        User teacherUser = userRepository.findByTeacherId(teacherDTO.getTeacherId());

        if (existingTeacher != null && existingTeacher.getTeacherId() != teacherDTO.getTeacherId()) {
            throw new TeacherAlreadyExistsException("Teacher with email " + teacherDTO.getEmail() + " already exists");
        }

        User user = userRepository.findByUsername(teacherDTO.getUsername());
        if (user != null && user.getTeacher().getTeacherId() != teacherDTO.getTeacherId()) {
            throw new TeacherAlreadyExistsException("User with username " + teacherDTO.getEmail() + " already exists");
        }
        else {
            teacherUser.setUsername(teacherDTO.getUsername());
        }
        if (!teacherUser.getPassword().equals(teacherDTO.getPassword())) {
            teacherUser.setPassword(passwordEncoder.encode(teacherDTO.getPassword()));
        }
        userRepository.save(teacherUser);
        Teacher updateEntity = new Teacher();
        updateEntity.setTeacherId(teacherDTO.getTeacherId());
        updateEntity.setEmail(teacherDTO.getEmail());
        updateEntity.setFullName(teacherDTO.getFullName());
        updateEntity.setSubject(teacherDTO.getSubject());
        updateEntity.setContactNumber(teacherDTO.getContactNumber());
        updateEntity.setYearsOfExperience(teacherDTO.getYearsOfExperience());
        teacherRepository.save(updateEntity);
    }

}