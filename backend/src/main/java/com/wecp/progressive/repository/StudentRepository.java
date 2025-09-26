package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStudentId(int studentId);

    Student findByEmail(String email);
}
