package org.alibou.demo.teacher;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.alibou.demo.common.BaseEntity;
import org.alibou.demo.subject.Subject;
import org.alibou.demo.user.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    private String speciality; // supposons ennou speciality marbout wella t7added l subject elli ynajm y9arrih l teacher


    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
