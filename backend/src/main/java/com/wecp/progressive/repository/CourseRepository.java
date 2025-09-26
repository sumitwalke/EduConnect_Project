package com.wecp.progressive.repository;

import com.wecp.progressive.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseId(int clinicId);

    Course findByCourseName(String courseName);

    @Query("Select c FROM Course c WHERE c.teacher.teacherId = :teacherId")
    List<Course> findAllByTeacherId(int teacherId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Course c WHERE c.teacher.teacherId = :teacherId")
    void deleteByTeacherId(int teacherId);
}
