package org.alibou.demo.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.address.Address;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.user.User;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "students", uniqueConstraints = {
    @UniqueConstraint(name = "students_email", columnNames = "email"),
    @UniqueConstraint(name = "students_username", columnNames = "username")
})
@DiscriminatorValue("STUDENT")
public class Student extends User {
  private LocalDate dateOfBirth;
  private String level;
  @OneToOne
  @JsonManagedReference
  private Address address;
  @ManyToMany
  @JoinTable(
      name = "subscription",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private Set<Subject> subjects = new HashSet<>();

}
