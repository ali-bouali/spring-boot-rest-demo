package org.alibou.demo.student;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.address.Address;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.user.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("STUDENT")
public class Student  extends User {

    private LocalDate dateOfBirth;
    private String level;

    @OneToOne(mappedBy = "student")
    private Address address;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;
}
