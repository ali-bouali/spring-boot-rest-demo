package org.alibou.demo.teacher;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
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

  @ManyToOne
 // @JoinColumn(name = "subject_id")
  @JoinTable(
      name = "subscription",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id")
  )
  private Subject subject;


}