package com.wecp.progressive.repository;

import com.wecp.progressive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.teacher.teacherId = :teacherId")
    User findByTeacherId(@Param("teacherId") int teacherId);

    @Query("SELECT u FROM User u WHERE u.student.studentId = :studentId")
    User findByStudentId(@Param("studentId") int studentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.teacher.teacherId = :teacherId")
    void deleteByTeacherId(@Param("teacherId") int teacherId);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.student.studentId = :studentId")
    void deleteByStudentId(@Param("studentId") int studentId);
}
