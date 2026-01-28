package com.hybrid.SecondarySchoolSystem.exceptions;

public class DuplicateTeacherException extends RuntimeException {
    public DuplicateTeacherException(String message) {
        super(message);
    }

    public DuplicateTeacherException(String message, Throwable cause) {
        super(message, cause);
    }
}
