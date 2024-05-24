package org.alibou.demo.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.address.Address;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.subject.Subject;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = {
    @UniqueConstraint(name = "students_email", columnNames = "email"),
    @UniqueConstraint(name = "students_username", columnNames = "username")
})
@SuperBuilder
public class Student extends BaseEntity {

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

  private String username;
  @Column(nullable = false)
  private String email;

  @Column(length = 100)
  private String firstname;
  @Column(length = 100, nullable = false)
  private String lastname;
  @OneToOne
  @JsonManagedReference
  private Address address;
  @ManyToMany(mappedBy = "students")
  private Set<Subject> subjects = new HashSet<>();

}
