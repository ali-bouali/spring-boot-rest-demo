package org.alibou.demo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private Integer id;
    @Column(updatable = false)
    private String username;
    private String email;

    @Column(length = 1000)
    private String firstname;
    private String lastname;
}
