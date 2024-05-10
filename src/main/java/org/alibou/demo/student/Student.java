package org.alibou.demo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull ;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.alibou.demo.address.Address;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import org.alibou.demo.subject.Subject;

@Data
@Entity
@Table(name = "students", uniqueConstraints = {
    @UniqueConstraint(name = "students_email", columnNames = "email"),
    @UniqueConstraint(name = "students_username", columnNames = "username")
})
public class Student {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "student_sequence",
        strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(updatable = false, nullable = false, length = 500)
    @NotNull(message = "The username should not be null")
    @Size(min = 3, max = 50)
    private String username;
    @Column(nullable = false)
    @NotNull(message = "The email should not be null")
    @Email
    private String email;

    @Column(length = 100)
    private String firstname;
    @Column(length = 100,nullable = false)
    @NotNull(message = "The lastname should not be null")
    private String lastname;
    @OneToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;
    @ManyToMany
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

}
