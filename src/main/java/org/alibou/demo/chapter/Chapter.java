package org.alibou.demo.chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.content.Content;
import org.alibou.demo.subject.Subject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chapters", uniqueConstraints = {
    @UniqueConstraint(name = "chapters_name", columnNames = "name")

})
@SuperBuilder
public class Chapter extends BaseEntity {

  @Column(nullable = false)
  private String title;
  @ManyToOne
  @JoinColumn(name = "subject_id")
  private Subject subject;
  @JsonIgnore
  @OneToMany(mappedBy = "chapter")
  private Set<Content> contents = new HashSet<>();
}
