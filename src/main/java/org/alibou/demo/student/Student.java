package org.alibou.demo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.address.Address;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.subject.Subject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Student  extends BaseEntity {


    @Column(updatable = false, nullable = false, unique = true)
    private String username;
    private String email;

    @Column(length = 1000)
    private String firstname;
    private String lastname;

    @OneToOne(mappedBy = "student")
    private Address address;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;
}
