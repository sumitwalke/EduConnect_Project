package com.wecp.progressive.service.impl;

import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.exception.StudentAlreadyExistsException;
import com.wecp.progressive.repository.AttendanceRepository;
import com.wecp.progressive.repository.EnrollmentRepository;
import com.wecp.progressive.repository.StudentRepository;
import com.wecp.progressive.repository.UserRepository;
import com.wecp.progressive.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImplJpa implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImplJpa(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public Integer addStudent(Student student) throws Exception {
        Student existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent != null) {
            throw new StudentAlreadyExistsException("Student with this email already exists, Email: " + student.getEmail());
        }
        return studentRepository.save(student).getStudentId();
    }

    @Override
    public List<Student> getAllStudentSortedByName() throws Exception {
        List<Student> studentList = studentRepository.findAll();
        studentList.sort(Comparator.comparing(Student::getFullName));
        return studentList;
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        Student existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent != null && existingStudent.getStudentId() != student.getStudentId()) {
            throw new StudentAlreadyExistsException("Student with this email already exists, Email: " + student.getEmail());
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int studentId) throws Exception {
        userRepository.deleteByStudentId(studentId);
        attendanceRepository.deleteByStudentId(studentId);
        enrollmentRepository.deleteByStudentId(studentId);
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentById(int studentId) throws Exception {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public void modifyStudentDetails(StudentDTO studentDTO) throws Exception {
        Student existingStudent = studentRepository.findByEmail(studentDTO.getEmail());
        User studentUser = userRepository.findByStudentId(studentDTO.getStudentId());
        
        if (existingStudent != null && existingStudent.getStudentId() != studentDTO.getStudentId()) {
            throw new StudentAlreadyExistsException("Student with email " + studentDTO.getEmail() + " already exists");
        }
        User user = userRepository.findByUsername(studentDTO.getUsername());
        if (user != null && user.getStudent().getStudentId() != studentDTO.getStudentId()) {
            throw new StudentAlreadyExistsException("User with username " + studentDTO.getEmail() + " already exists");
        }
        else {
            studentUser.setUsername(studentDTO.getUsername());
        }
        if (!studentUser.getPassword().equals(studentDTO.getPassword())) {
            studentUser.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }
        userRepository.save(studentUser);
        Student updateEntity = new Student();
        updateEntity.setStudentId(studentDTO.getStudentId());
        updateEntity.setFullName(studentDTO.getFullName());
        updateEntity.setDateOfBirth(studentDTO.getDateOfBirth());
        updateEntity.setEmail(studentDTO.getEmail());
        updateEntity.setContactNumber(studentDTO.getContactNumber());
        updateEntity.setAddress(studentDTO.getAddress());
        studentRepository.save(updateEntity);
    }
}