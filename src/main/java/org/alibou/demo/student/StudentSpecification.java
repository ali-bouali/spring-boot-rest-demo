package org.alibou.demo.student;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class StudentSpecification {


    // where s.createdAt = :createdAt
    public static Specification<Student> withCreatedAt(LocalDateTime createdAt) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdAt"), createdAt));
    }

    // where s.email = :email
    public static Specification<Student> withEmail(String email) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email));
    }

    public static Specification<Student> withFirstname(String fn) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstname"), fn));
    }

    public static Specification<Student> withLastname(String ln) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastname"), ln));
    }

    public static Specification<Student> withSubjectIds(List<Integer> ids) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(
                        root.get("subjects")
                                .get("id"))
                .value(ids)
        );
    }

    /**
     * criteria with nested objects / attributes
     * @param street
     * @return
     */
    public static Specification<Student> withAddressStreet(String street) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("address").get("street"), street));
    }


}
