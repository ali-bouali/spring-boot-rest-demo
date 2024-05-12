package org.alibou.demo.content;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.subject.Subject;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
public class Content {

    @Id
    @GeneratedValue
    private  Integer id;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
