package com.wecp.progressive.exception;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}