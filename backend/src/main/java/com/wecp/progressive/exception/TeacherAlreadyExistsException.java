package com.wecp.progressive.exception;

public class TeacherAlreadyExistsException extends RuntimeException {
    public TeacherAlreadyExistsException(String message) {
        super(message);
    }
}