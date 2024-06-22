package org.alibou.demo.teacher;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.user.User;

@Data
@Entity
@Table(name = "teachers", uniqueConstraints = {
    @UniqueConstraint(name = "teachers_name", columnNames = "name")

})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
  private String speciality;

  @ManyToOne
  @JoinTable(
      name = "subscription",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private Subject subject;


}