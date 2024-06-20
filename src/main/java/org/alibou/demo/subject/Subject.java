package org.alibou.demo.subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.common.BaseEntity;
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
@SuperBuilder
public class Subject extends BaseEntity {

  @Column(nullable = false)
  private String name;
  private Integer capacity;
  private String description;
  @JsonIgnore
  @ManyToMany(mappedBy = "subjects")
  private Set<Student> students = new HashSet<>();
  @JsonIgnore
  @OneToMany(mappedBy = "subject")
  private Set<Teacher> teachers = new HashSet<>();
  @JsonIgnore
  @OneToMany(mappedBy = "subject")
  private Set<Chapter> chapters = new HashSet<>();

}



