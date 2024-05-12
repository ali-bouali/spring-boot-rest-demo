package org.alibou.demo.subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.student.Student;
import org.alibou.demo.teacher.Teacher;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private  Integer id;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "subject")
    private List<Chapter> chapters;

    @ManyToMany // -->
    @JoinTable(
            name = "Subscription",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;
}
