package org.alibou.demo.exception;

import lombok.Getter;

public enum ExceptionType {

    SUBJECT_LIMIT_EXCEEDED(1, "subject limit exceeded"),
    TEACHER_NOT_ASSIGNABLE_TO_SUBJECT(2, "bla bla bla"),
    PAYMENT_EXCEPTION(22, "dasdasd"),
    NO_DEFINED(0, "not defined")

    ;

    @Getter
    private final int code;
    @Getter
    private final String desc;

    ExceptionType(int code, String  desc) {
        this.code = code;
        this.desc = desc;
    }
}
