package org.alibou.demo.teacher;

import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecification {

  public static Specification<Teacher> withCreatedAt(LocalDateTime createdAt) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdAt"),
        createdAt));
  }

  public static Specification<Teacher> withFirstname(String fn) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstname"), fn));
  }

  public static Specification<Teacher> withLastname(String ln) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastname"), ln));
  }

  public static Specification<Teacher> withSubjectId(Integer id) {
    return ((root, query, criteriaBuilder) -> criteriaBuilder.in(
            root.get("subject")
                .get("id"))
        .value(id)
    );
  }// askali for the trim for the specification

}
