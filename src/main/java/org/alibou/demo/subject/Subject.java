package org.alibou.demo.subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.student.Student;
import org.alibou.demo.teacher.Teacher;

@Table(name = "subjects", uniqueConstraints = {
    @UniqueConstraint(name = "subjects_name", columnNames = "name")

})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
  @Id
  @SequenceGenerator(
      name = "subject_sequence",
      sequenceName = "subject_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      generator = "subject_sequence",
      strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(nullable=false)
  private String name;
  private String description;
  @ManyToMany(mappedBy = "subjects")
  private Set<Student> students = new HashSet<>();
  @OneToMany(mappedBy = "subject")
  private Set<Teacher> teachers = new HashSet<>();
  @OneToMany(mappedBy = "subject")
  private Set<Chapter> chapters = new HashSet<>();
}



