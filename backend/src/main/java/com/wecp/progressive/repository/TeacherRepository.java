package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByTeacherId(int teacherId);

    Teacher findByEmail(String email);
}
