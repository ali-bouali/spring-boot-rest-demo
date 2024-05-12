package org.alibou.demo.chapter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.content.Content;
import org.alibou.demo.subject.Subject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Chapter extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "chapter")
    private List<Content> contents;
}
