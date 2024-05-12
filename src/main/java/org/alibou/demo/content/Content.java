package org.alibou.demo.content;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "contents")
public  class Content {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "content_sequence")
  @SequenceGenerator(name = "content_sequence", sequenceName = "content_sequence", allocationSize = 1)
  private Long id;

  @Column(nullable = false)
  private String content;
  @ManyToOne
  @JoinColumn(name = "chapter_id", nullable = false)
  private Chapter chapter;
}
