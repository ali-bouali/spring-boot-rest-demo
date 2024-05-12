package org.alibou.demo.chapter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alibou.demo.content.Content;
import org.alibou.demo.subject.Subject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chapter {

    @Id
    @GeneratedValue
    private  Integer id;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "chapter")
    private List<Content> contents;
}
