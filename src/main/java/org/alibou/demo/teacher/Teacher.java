package org.alibou.demo.teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.alibou.demo.subject.Subject;
@Data
@Entity
@Table(name = "teachers", uniqueConstraints = {
    @UniqueConstraint(name = "teachers_name", columnNames = "name")

})
public class Teacher {
  @Id
  @SequenceGenerator(
      name = "teacher_sequence",
      sequenceName = "teacher_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "teacher_sequence",
      strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  @NotNull(message="The name should not be null")
  private String name;

  // A teacher can teach only one subject
  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

}