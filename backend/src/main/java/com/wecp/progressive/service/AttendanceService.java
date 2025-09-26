package com.wecp.progressive.service;

import com.wecp.progressive.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> getAllAttendance();

    Attendance createAttendance(Attendance attendance);

    void deleteAttendance(int attendanceId);

    List<Attendance> getAttendanceByStudent(int studentId);

    List<Attendance> getAttendanceByCourse(int courseId);
}
