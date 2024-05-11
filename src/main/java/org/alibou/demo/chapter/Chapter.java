package org.alibou.demo.chapter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.alibou.demo.content.Content;
import org.alibou.demo.subject.Subject;

@Data
@Entity
@Table(name = "chapters", uniqueConstraints = {
    @UniqueConstraint(name = "chapters_name", columnNames = "name")

})
public class Chapter {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chapter_sequence")
  @SequenceGenerator(name = "chapter_sequence", sequenceName = "chapter_sequence", allocationSize = 1)
  private Long id;

  @Column(nullable = false)
  private String title;
  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;

  @OneToMany(mappedBy = "chapter")
  private Set<Content> contents = new HashSet<>();
}
