package org.alibou.demo.subject;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class SubjectSpecification {
  public static Specification<Subject> withCreatedAtSubject(LocalDateTime createdAt) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdAt"), createdAt));
  }
  public static Specification<Subject> withCapacity(Integer capacity) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("capacity"), capacity));
  }
  public static Specification<Subject> withName(String name) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name));
  }

 }
