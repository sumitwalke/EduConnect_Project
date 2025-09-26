package com.wecp.progressive.service;

import com.wecp.progressive.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();

    int createEnrollment(Enrollment enrollment);

    public void updateEnrollment(Enrollment enrollment);

    public Enrollment getEnrollmentById(int enrollmentId);

    public List<Enrollment> getAllEnrollmentsByStudent(int studentId);

    public List<Enrollment> getAllEnrollmentsByCourse(int courseId);

}
