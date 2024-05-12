package org.alibou.demo.content;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.chapter.Chapter;
import org.alibou.demo.common.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
public class Content extends BaseEntity {

    private String description;

    @ManyToOne()
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
