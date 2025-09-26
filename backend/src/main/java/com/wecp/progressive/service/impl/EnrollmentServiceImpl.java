package com.wecp.progressive.service.impl;
import com.wecp.progressive.entity.Enrollment;
import com.wecp.progressive.repository.CourseRepository;
import com.wecp.progressive.repository.EnrollmentRepository;
import com.wecp.progressive.repository.StudentRepository;
import com.wecp.progressive.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public int createEnrollment(Enrollment enrollment) {
        int studentId = enrollment.getStudent().getStudentId();
        int courseId = enrollment.getCourse().getCourseId();

        if (enrollmentRepository.findByStudent_StudentIdAndCourse_CourseId(studentId, courseId).isPresent()) {
            throw new RuntimeException("Student is already enrolled in this course.");
        }

        enrollment.setEnrollmentDate(new Date());
        return enrollmentRepository.save(enrollment).getEnrollmentId();
    }

    @Override
    public void updateEnrollment(Enrollment updatedEnrollment) {
        Enrollment existingEnrollment = enrollmentRepository.findById(updatedEnrollment.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + updatedEnrollment.getEnrollmentId()));

        existingEnrollment.setStudent(updatedEnrollment.getStudent());
        existingEnrollment.setCourse(updatedEnrollment.getCourse());
        existingEnrollment.setEnrollmentDate(new Date());

        enrollmentRepository.save(existingEnrollment);
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + enrollmentId));
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByStudent(int studentId) {
        return enrollmentRepository.findAllByStudent_StudentId(studentId);
    }

    @Override
    public List<Enrollment> getAllEnrollmentsByCourse(int courseId) {
        return enrollmentRepository.findAllByCourse_CourseId(courseId);
    }
}
