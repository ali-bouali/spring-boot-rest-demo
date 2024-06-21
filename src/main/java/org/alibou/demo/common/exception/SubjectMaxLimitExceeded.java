package org.alibou.demo.common.exception;

public class SubjectMaxLimitExceeded
    extends RuntimeException {

  public SubjectMaxLimitExceeded(String message) {
    super(message);
  }

}