package org.alibou.demo.exception;

import lombok.Getter;

@Getter
public class SubjectMaxLimitExceeded extends RuntimeException {

    private final int maxCapacity;
    public SubjectMaxLimitExceeded(String message, int maxCapacity) {
        super(message);
        this.maxCapacity = maxCapacity;
    }
}
