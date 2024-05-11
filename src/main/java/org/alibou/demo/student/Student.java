package org.alibou.demo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ECD_IJK_STD")
public class Student { // ECD_IJK_STD

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "tbl"
    )
    @TableGenerator(
            name = "tbl",
            initialValue = 1,
            allocationSize = 1
    )
    private Integer id;
    @Column(updatable = false, nullable = false, unique = true)
    private String username;
    private String email;

    @Column(length = 1000)
    private String firstname;
    private String lastname;
}
